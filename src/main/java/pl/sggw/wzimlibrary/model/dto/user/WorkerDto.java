package pl.sggw.wzimlibrary.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.sggw.wzimlibrary.model.constant.Gender;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WorkerDto {
    private String firstName;
    private String lastName;
    private String email;
    private Gender gender;
}

