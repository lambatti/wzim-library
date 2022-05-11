package pl.sggw.wzimlibrary.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import pl.sggw.wzimlibrary.exception.UserNotFoundException;
import pl.sggw.wzimlibrary.model.annotation.CurrentlyLoggedUser;
import pl.sggw.wzimlibrary.model.dto.user.UserPanelChangePasswordDto;
import pl.sggw.wzimlibrary.service.UserService;

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

    @GetMapping("user/summary")
    ResponseEntity<?> getUserSummary(@CurrentlyLoggedUser UserDetails userDetails) throws UserNotFoundException {
        return ResponseEntity.ok(userService.getUserSummary(userDetails));
    }

    @PatchMapping("/user/changePassword")
    ResponseEntity<?> changePassword(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestBody UserPanelChangePasswordDto userPanelChangePasswordDto) throws ExecutionException, InterruptedException {
        if (userService.changePassword(token, userPanelChangePasswordDto)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
