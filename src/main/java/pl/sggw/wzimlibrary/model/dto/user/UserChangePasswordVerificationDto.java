package pl.sggw.wzimlibrary.model.dto.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.sggw.wzimlibrary.model.constant.SecurityQuestion;
import pl.sggw.wzimlibrary.model.constant.ValidationConstant;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@RequiredArgsConstructor
@Getter
public class UserChangePasswordVerificationDto {

    @NotEmpty
    @Email
    private final String email;

    @NotNull
    private final SecurityQuestion securityQuestion;

    @NotEmpty
    @Size(max = ValidationConstant.SECURITY_QUESTION_ANSWER_MAX_LENGTH)
    private final String securityQuestionAnswer;
}
