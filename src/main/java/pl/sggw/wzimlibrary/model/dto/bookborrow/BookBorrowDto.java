package pl.sggw.wzimlibrary.model.dto.bookborrow;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookBorrowDto extends BookBorrowBaseDto {

    private LocalDate borrowDate;
    private LocalDate returnDate;

}
