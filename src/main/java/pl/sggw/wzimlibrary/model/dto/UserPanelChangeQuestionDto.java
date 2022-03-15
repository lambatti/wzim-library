package pl.sggw.wzimlibrary.model.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.sggw.wzimlibrary.model.SecurityQuestion;
import pl.sggw.wzimlibrary.model.ValidationLength;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@RequiredArgsConstructor
@Getter
public class UserPanelChangeQuestionDto {

    @NotEmpty
    @Size(min = ValidationLength.PASSWORD_MIN_LENGTH,
            max = ValidationLength.PASSWORD_MAX_LENGTH)
    private final String password;

    @NotEmpty
    @Size(max = ValidationLength.SECURITY_QUESTION_MAX_LENGTH)
    private final SecurityQuestion securityQuestion;

    @NotEmpty
    @Size(max = ValidationLength.SECURITY_QUESTION_ANSWER_MAX_LENGTH)
    private final String securityQuestionAnswer;

}
