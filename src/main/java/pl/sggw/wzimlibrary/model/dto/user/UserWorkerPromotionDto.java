package pl.sggw.wzimlibrary.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserWorkerPromotionDto {
    @NotEmpty
    @Email
    private String email;
}
