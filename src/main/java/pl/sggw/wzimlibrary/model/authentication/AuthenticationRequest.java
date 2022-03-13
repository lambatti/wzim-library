package pl.sggw.wzimlibrary.model.authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.sggw.wzimlibrary.model.ValidationLength;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
@Setter
public class AuthenticationRequest {

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Size(min = ValidationLength.PASSWORD_MIN_LENGTH,
            max = ValidationLength.PASSWORD_MAX_LENGTH)
    private String password;

}
