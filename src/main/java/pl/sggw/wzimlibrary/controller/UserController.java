package pl.sggw.wzimlibrary.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.sggw.wzimlibrary.model.User;
import pl.sggw.wzimlibrary.model.dto.UserRegistrationDto;
import pl.sggw.wzimlibrary.service.UserService;

import javax.validation.Valid;
import java.net.URI;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("users")
    ResponseEntity<?> getAllUsers() throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(userService.findAll().get());
    }

    @PostMapping("user/register")
    ResponseEntity<?> registerUser(@Valid @RequestBody UserRegistrationDto userRegistrationDto) throws ExecutionException, InterruptedException {
        User user = userService.registerUser(userRegistrationDto);
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.created(
                URI.create(ServletUriComponentsBuilder.fromCurrentRequest().build() + "/" + user.getId())).build();
    }

}
