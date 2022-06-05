package pl.sggw.wzimlibrary.model.dto.bookborrow;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookBorrowProlongationRequestDto extends BookBorrowBaseDto {

    private String firstName;
    private String lastName;
    private String email;
    private LocalDate borrowDate;
    private LocalDate requestDate;
    private LocalDate prolongationDate;

}
