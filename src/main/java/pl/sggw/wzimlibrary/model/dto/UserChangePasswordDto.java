package pl.sggw.wzimlibrary.model.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.sggw.wzimlibrary.model.constant.ValidationConstant;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@RequiredArgsConstructor
@Getter
public class UserChangePasswordDto {

    @NotEmpty
    @Size(min = ValidationConstant.PASSWORD_MIN_LENGTH,
            max = ValidationConstant.PASSWORD_MAX_LENGTH)
    private final String newPassword;

    @NotEmpty
    @Size(min = ValidationConstant.PASSWORD_MIN_LENGTH,
            max = ValidationConstant.PASSWORD_MAX_LENGTH)
    private final String newPasswordConfirmation;
}
