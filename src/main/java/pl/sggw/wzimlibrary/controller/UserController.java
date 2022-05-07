package pl.sggw.wzimlibrary.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import pl.sggw.wzimlibrary.exception.NotAdminException;
import pl.sggw.wzimlibrary.exception.PasswordMismatchException;
import pl.sggw.wzimlibrary.exception.SecurityQuestionAnswerMismatchException;
import pl.sggw.wzimlibrary.exception.UserNotFoundException;
import pl.sggw.wzimlibrary.model.annotation.CurrentlyLoggedUser;
import pl.sggw.wzimlibrary.model.dto.user.UserForgottenPasswordDto;
import pl.sggw.wzimlibrary.model.dto.user.UserPanelChangePasswordDto;
import pl.sggw.wzimlibrary.model.dto.user.UserPanelChangeQuestionDto;
import pl.sggw.wzimlibrary.model.dto.user.UserWorkerPromotionDto;
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
    ResponseEntity<?> changePassword(@CurrentlyLoggedUser UserDetails userDetails, @RequestBody UserPanelChangePasswordDto userPanelChangePasswordDto) throws UserNotFoundException, PasswordMismatchException, ExecutionException, InterruptedException {
        userService.changePassword(userDetails, userPanelChangePasswordDto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/user/forgottenPassword")
    ResponseEntity<?> forgottenPassword(@RequestBody UserForgottenPasswordDto userForgottenPasswordDto) throws ExecutionException, InterruptedException, SecurityQuestionAnswerMismatchException, PasswordMismatchException {
        userService.changeForgottenPassword(userForgottenPasswordDto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/user/changeQuestion")
    ResponseEntity<?> changeQuestion(@CurrentlyLoggedUser UserDetails userDetails, @RequestBody UserPanelChangeQuestionDto userPanelChangeQuestionDto) throws UserNotFoundException, PasswordMismatchException, ExecutionException, InterruptedException {
        userService.changeQuestion(userDetails, userPanelChangeQuestionDto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/user/workerPromotion")
    ResponseEntity<?> promoteWorker(@CurrentlyLoggedUser UserDetails userDetails, @RequestBody UserWorkerPromotionDto userWorkerPromotionDto) throws UserNotFoundException, NotAdminException, ExecutionException, InterruptedException {
        userService.promoteWorker(userDetails, userWorkerPromotionDto);
        return ResponseEntity.ok().build();
    }
}
