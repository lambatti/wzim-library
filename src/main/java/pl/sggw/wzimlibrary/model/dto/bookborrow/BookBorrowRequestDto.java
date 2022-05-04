package pl.sggw.wzimlibrary.model.dto.bookborrow;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookBorrowRequestDto {

    private Integer userId;
    private String bookSlug;
    private LocalDate requestDate;

}
