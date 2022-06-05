package pl.sggw.wzimlibrary.model.dto.bookborrow;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookBorrowRequestDto extends BookBorrowBaseDto {

    private String firstName;
    private String lastName;
    private String email;
    private LocalDate requestDate;

}
