package pl.sggw.wzimlibrary.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.sggw.wzimlibrary.model.constant.SecurityQuestion;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@Getter
public class UserForgottenPasswordDto {
    @NotEmpty
    private final String email;

    @NotEmpty
    private final SecurityQuestion question;

    @NotEmpty
    private final String answer;

    @NotEmpty
    private final String newPassword;

    @NotEmpty
    private final String newPasswordConfirmation;
}
