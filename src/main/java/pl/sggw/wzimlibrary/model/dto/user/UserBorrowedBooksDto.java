package pl.sggw.wzimlibrary.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserBorrowedBooksDto {

    private String title;
    private List<String> authors;
    private String cover;
    private String bookSlug;
}
