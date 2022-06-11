package pl.sggw.wzimlibrary.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.sggw.wzimlibrary.exception.UserAlreadyExistsException;
import pl.sggw.wzimlibrary.model.User;
import pl.sggw.wzimlibrary.model.dto.user.UserRegistrationDto;
import pl.sggw.wzimlibrary.service.UserService;

import javax.validation.Valid;
import java.net.URI;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/")
@CrossOrigin
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @PostMapping("registerUser")
    ResponseEntity<?> registerUser(@Valid @RequestBody UserRegistrationDto userRegistrationDto)
            throws ExecutionException, InterruptedException, UserAlreadyExistsException {

        User registeredUser = userService.registerUser(userRegistrationDto);

        return ResponseEntity.created(URI.create(
                        ServletUriComponentsBuilder.fromCurrentRequest().build() + "/" + registeredUser.getId()))
                .build();


    }

}
