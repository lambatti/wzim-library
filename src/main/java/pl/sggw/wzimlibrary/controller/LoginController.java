package pl.sggw.wzimlibrary.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sggw.wzimlibrary.exception.UserNotFoundException;
import pl.sggw.wzimlibrary.model.authentication.AuthenticationRequest;
import pl.sggw.wzimlibrary.service.LoginService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/")
@CrossOrigin
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("login")
    ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody AuthenticationRequest authenticationRequest) throws UserNotFoundException {
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, loginService.generateToken(authenticationRequest))
                .build();
    }
}
