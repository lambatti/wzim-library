package pl.sggw.wzimlibrary.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.sggw.wzimlibrary.model.Gender;
import pl.sggw.wzimlibrary.model.Role;
import pl.sggw.wzimlibrary.model.SecurityQuestion;
import pl.sggw.wzimlibrary.model.ValidationConstant;

import javax.validation.constraints.*;

@AllArgsConstructor
@Getter
public class UserRegistrationDto {

    @NotEmpty
    @Size(max = ValidationConstant.FIRST_NAME_MAX_LENGTH)
    @Pattern(regexp = ValidationConstant.NAME_REGEXP)
    private final String firstName;

    @NotEmpty
    @Size(max = ValidationConstant.LAST_NAME_MAX_LENGTH)
    @Pattern(regexp = ValidationConstant.NAME_REGEXP)
    private final String lastName;

    @NotEmpty
    @Email
    private final String email;

    // to allow password encryption
    @Setter
    @NotEmpty
    @Size(min = ValidationConstant.PASSWORD_MIN_LENGTH,
            max = ValidationConstant.PASSWORD_MAX_LENGTH)
    private String password;

    @NotNull
    private final Gender gender;

    @NotNull
    private final SecurityQuestion securityQuestion;

    @NotEmpty
    @Size(max = ValidationConstant.SECURITY_QUESTION_ANSWER_MAX_LENGTH)
    private final String securityQuestionAnswer;

    private final Role role = Role.USER;

}
