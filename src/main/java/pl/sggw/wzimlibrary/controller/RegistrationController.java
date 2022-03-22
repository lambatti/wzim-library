package pl.sggw.wzimlibrary.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
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
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;
    private final RegistrationService registrationService;

    @PostMapping("sendRegistrationMail")
    public ResponseEntity<?> sendRegistrationMail(@Valid @RequestBody UserRegistrationDto userRegistrationDto) throws MessagingException, JsonProcessingException {
        registrationService.sendRegistrationMail(userRegistrationDto,
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .replacePath("/api")
                        .build()
                        .toUriString());
        return ResponseEntity.ok().build();
    }

    @GetMapping("activateAccount")
    public ResponseEntity<?> activateAccount(@RequestParam String user) throws IOException, ExecutionException, InterruptedException {
        return registerUser(registrationService.getUserFromEncryptedMessage(user));
    }

    @PostMapping("register")
    ResponseEntity<?> registerUser(@Valid @RequestBody UserRegistrationDto userRegistrationDto) throws ExecutionException, InterruptedException {

        return userService.registerUser(userRegistrationDto).map(
                        user -> ResponseEntity.created(
                                URI.create(ServletUriComponentsBuilder.fromCurrentRequest().build() + "/" + user.getId())))
                .orElseGet(ResponseEntity::badRequest).build();
    }


}
