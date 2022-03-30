package pl.sggw.wzimlibrary.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sggw.wzimlibrary.model.dto.UserPanelChangePasswordDto;
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

    @PatchMapping("/user/changePassword")
    ResponseEntity<?> changePassword(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestBody UserPanelChangePasswordDto userPanelChangePasswordDto) throws ExecutionException, InterruptedException {
        if (userService.changePassword(token, userPanelChangePasswordDto)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
