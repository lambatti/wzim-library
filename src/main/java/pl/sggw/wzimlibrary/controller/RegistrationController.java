package pl.sggw.wzimlibrary.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.sggw.wzimlibrary.exception.UserAlreadyExistsException;
import pl.sggw.wzimlibrary.exception.UserDecryptionException;
import pl.sggw.wzimlibrary.model.User;
import pl.sggw.wzimlibrary.model.dto.UserRegistrationDto;
import pl.sggw.wzimlibrary.service.RegistrationService;
import pl.sggw.wzimlibrary.service.UserService;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/")
@CrossOrigin
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;
    private final RegistrationService registrationService;

    @PostMapping("sendRegistrationMail")
    public ResponseEntity<?> sendRegistrationMail(@Valid @RequestBody UserRegistrationDto userRegistrationDto)
            throws MessagingException, JsonProcessingException {

        registrationService.sendRegistrationMail(userRegistrationDto,
                ServletUriComponentsBuilder.fromCurrentRequest().replacePath("/api").build().toUriString());

        return ResponseEntity.ok().build();

    }

    @GetMapping("activateAccount")
    public ResponseEntity<?> activateAccount(@RequestParam String user)
            throws IOException, ExecutionException, InterruptedException, UserAlreadyExistsException,
            UserDecryptionException {

        return registerUser(registrationService.getUserFromEncryptedMessage(user));

    }

    @PostMapping("register")
    ResponseEntity<?> registerUser(@Valid @RequestBody UserRegistrationDto userRegistrationDto)
            throws ExecutionException, InterruptedException, UserAlreadyExistsException {

        User registeredUser = userService.registerUser(userRegistrationDto);

        return ResponseEntity.created(URI.create(
                        ServletUriComponentsBuilder.fromCurrentRequest().build() + "/" + registeredUser.getId()))
                .build();


    }

}
