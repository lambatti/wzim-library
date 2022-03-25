package pl.sggw.wzimlibrary.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.sggw.wzimlibrary.model.User;
import pl.sggw.wzimlibrary.model.dto.UserChangePasswordDto;
import pl.sggw.wzimlibrary.model.dto.UserPanelChangePasswordDto;
import pl.sggw.wzimlibrary.model.dto.UserRegistrationDto;
import pl.sggw.wzimlibrary.service.UserService;

import javax.validation.Valid;
import java.net.URI;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/")
@CrossOrigin
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("users")
    ResponseEntity<?> getAllUsers() throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(userService.findAll().get());
    }

    @PostMapping("user/register")
    ResponseEntity<?> registerUser(@Valid @RequestBody UserRegistrationDto userRegistrationDto) throws ExecutionException, InterruptedException {

        return userService.registerUser(userRegistrationDto).map(
                        user -> ResponseEntity.created(
                                URI.create(ServletUriComponentsBuilder.fromCurrentRequest().build() + "/" + user.getId())))
                .orElseGet(ResponseEntity::badRequest).build();
    }

    @PatchMapping("/user/changePassword")
    ResponseEntity<?> changePassword(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestBody UserPanelChangePasswordDto userPanelChangePasswordDto) throws ExecutionException, InterruptedException {
        if (userService.changePassword(token, userPanelChangePasswordDto)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
