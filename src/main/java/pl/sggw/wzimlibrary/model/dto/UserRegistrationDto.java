package pl.sggw.wzimlibrary.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.sggw.wzimlibrary.model.Gender;
import pl.sggw.wzimlibrary.model.Role;
import pl.sggw.wzimlibrary.model.SecurityQuestion;
import pl.sggw.wzimlibrary.model.ValidationLength;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
public class UserRegistrationDto {

    @NotEmpty
    @Size(max = ValidationLength.FIRST_NAME_MAX_LENGTH)
    private final String firstName;

    @NotEmpty
    @Size(max = ValidationLength.LAST_NAME_MAX_LENGTH)
    private final String lastName;

    @NotEmpty
    @Email
    private final String email;

    // to allow password encryption
    @Setter
    @NotEmpty
    @Size(min = ValidationLength.PASSWORD_MIN_LENGTH,
            max = ValidationLength.PASSWORD_MAX_LENGTH)
    private String password;

    @NotEmpty
    private final Gender gender;

    @NotEmpty
    @Size(max = ValidationLength.SECURITY_QUESTION_MAX_LENGTH)
    private final SecurityQuestion securityQuestion;

    @NotEmpty
    @Size(max = ValidationLength.SECURITY_QUESTION_ANSWER_MAX_LENGTH)
    private final String securityQuestionAnswer;

    private final Role role = Role.USER;

}
