package pl.sggw.wzimlibrary.model.authentication;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class AuthenticationResponse {

    private final String token;

}
