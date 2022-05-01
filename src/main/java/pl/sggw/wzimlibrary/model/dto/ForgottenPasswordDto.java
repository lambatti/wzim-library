package pl.sggw.wzimlibrary.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@Getter
class ForgottenPasswordDto {
    @NotEmpty
    private final String email;

    @NotEmpty
    private final String question;

    @NotEmpty
    private final String answer;

    @NotEmpty
    private final String newPassword;

    @NotEmpty
    private final String repeatNewPassword;
}
