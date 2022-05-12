package pl.sggw.wzimlibrary.model.dto.bookborrow;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookBorrowRequestDto {

    private Integer userId;
    private String bookSlug;
    private String firstName;
    private String lastName;
    private int borrowedBooks;
    private int readBooks;
    private LocalDate requestDate;

}
