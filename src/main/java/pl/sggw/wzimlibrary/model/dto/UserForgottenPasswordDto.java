package pl.sggw.wzimlibrary.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.sggw.wzimlibrary.model.constant.SecurityQuestion;
import pl.sggw.wzimlibrary.model.constant.ValidationConstant;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
public class UserForgottenPasswordDto {
    @NotEmpty
    @Email
    private final String email;

    @NotEmpty
    @Size(max = ValidationConstant.SECURITY_QUESTION_MAX_LENGTH)
    private final SecurityQuestion question;

    @NotEmpty
    @Size(max = ValidationConstant.SECURITY_QUESTION_ANSWER_MAX_LENGTH)
    private final String answer;

    @NotEmpty
    @Size(min = ValidationConstant.PASSWORD_MIN_LENGTH,
            max = ValidationConstant.PASSWORD_MAX_LENGTH)
    private final String newPassword;

    @NotEmpty
    @Size(min = ValidationConstant.PASSWORD_MIN_LENGTH,
            max = ValidationConstant.PASSWORD_MAX_LENGTH)
    private final String newPasswordConfirmation;
}
