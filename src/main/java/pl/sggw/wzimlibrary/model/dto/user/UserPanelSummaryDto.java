package pl.sggw.wzimlibrary.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserPanelSummaryDto {

    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private int borrowedBooks;
    private int readBooks;

}
