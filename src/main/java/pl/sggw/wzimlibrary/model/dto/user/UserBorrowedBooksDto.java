package pl.sggw.wzimlibrary.model.dto.user;

import lombok.*;

import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
public class UserBorrowedBooksDto {
    private String title;
    private List<String> authors;
    private String cover;
    private List<String> kinds;
    private String slug;
}
