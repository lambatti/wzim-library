package pl.sggw.wzimlibrary.model.dto.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.*;

@RequiredArgsConstructor
@Getter
public class UserWorkerPromotionDto {
    @NotEmpty
    @Email
    private String email;
}
