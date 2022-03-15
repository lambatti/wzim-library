package pl.sggw.wzimlibrary.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.sggw.wzimlibrary.model.Gender;
import pl.sggw.wzimlibrary.model.Role;
import pl.sggw.wzimlibrary.model.SecurityQuestion;

@AllArgsConstructor
@Getter
public class UserRegistrationDto {

    private final String firstName;
    private final String lastName;
    private final String email;

    // to allow password encryption
    @Setter
    private String password;

    private final Gender gender;
    private final SecurityQuestion securityQuestion;
    private final String securityQuestionAnswer;
    private final Role role = Role.USER;

}
