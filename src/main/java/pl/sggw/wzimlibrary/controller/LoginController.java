package pl.sggw.wzimlibrary.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody AuthenticationRequest authenticationRequest) {
        return loginService.generateToken(authenticationRequest).map(
                        token -> (ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, token)))
                .orElseGet(ResponseEntity::badRequest).build();
    }
}
