package pl.sggw.wzimlibrary.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import pl.sggw.wzimlibrary.exception.PasswordMissmatchException;
import pl.sggw.wzimlibrary.exception.SecurityQuestionAnswerMissmatchException;
import pl.sggw.wzimlibrary.exception.UserNotFoundException;
import pl.sggw.wzimlibrary.model.annotation.CurrentlyLoggedUser;
import pl.sggw.wzimlibrary.model.dto.user.UserForgottenPasswordDto;
import pl.sggw.wzimlibrary.model.dto.user.UserPanelChangePasswordDto;
import pl.sggw.wzimlibrary.model.dto.user.UserPanelChangeQuestionDto;
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
    ResponseEntity<?> changePassword(@CurrentlyLoggedUser UserDetails userDetails, @RequestBody UserPanelChangePasswordDto userPanelChangePasswordDto) throws UserNotFoundException, PasswordMissmatchException {
        userService.changePassword(userDetails, userPanelChangePasswordDto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/user/forgottenPassword")
    ResponseEntity<?> forgottenPassword(@RequestBody UserForgottenPasswordDto userForgottenPasswordDto) throws ExecutionException, InterruptedException, SecurityQuestionAnswerMissmatchException, PasswordMissmatchException {
        userService.forgottenPasswordChange(userForgottenPasswordDto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/user/changeQuestion")
    ResponseEntity<?> changeQuestion(@CurrentlyLoggedUser UserDetails userDetails, @RequestBody UserPanelChangeQuestionDto userPanelChangeQuestionDto) throws UserNotFoundException, SecurityQuestionAnswerMissmatchException, ExecutionException, InterruptedException, PasswordMissmatchException {
        userService.changeQuestion(userDetails, userPanelChangeQuestionDto);
        return ResponseEntity.ok().build();
    }
}
