package pl.sggw.wzimlibrary.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.sggw.wzimlibrary.model.constant.Gender;
import pl.sggw.wzimlibrary.model.constant.Role;
import pl.sggw.wzimlibrary.model.constant.SecurityQuestion;
import pl.sggw.wzimlibrary.model.constant.ValidationConstant;

import javax.validation.constraints.*;

@AllArgsConstructor
@Getter
public class UserRegistrationDto {

    @NotEmpty
    @Size(min = ValidationConstant.STRING_MIN_LENGTH,
            max = ValidationConstant.FIRST_NAME_MAX_LENGTH)
    @Pattern(regexp = ValidationConstant.NAME_REGEXP)
    private final String firstName;

    @NotEmpty
    @Size(min = ValidationConstant.STRING_MIN_LENGTH,
            max = ValidationConstant.LAST_NAME_MAX_LENGTH)
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
    @Size(min = ValidationConstant.STRING_MIN_LENGTH,
            max = ValidationConstant.SECURITY_QUESTION_ANSWER_MAX_LENGTH)
    private final String securityQuestionAnswer;

    private final Role role = Role.USER;

}
