package pl.sggw.wzimlibrary.model.dto.user;

import lombok.Data;

@Data
public class UserPanelSummaryDto {

    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private int borrowedBooks;
    private int readBooks;

}
