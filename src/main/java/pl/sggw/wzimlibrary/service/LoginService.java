package pl.sggw.wzimlibrary.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pl.sggw.wzimlibrary.model.authentication.AuthenticationRequest;
import pl.sggw.wzimlibrary.util.JwtUtil;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public Optional<String> generateToken(AuthenticationRequest authenticationRequest) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
                            authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            return Optional.empty();
        }

        UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getEmail());
        String jwt = "Bearer " + jwtUtil.generateToken(userDetails);
        return Optional.of(jwt);
    }

}
