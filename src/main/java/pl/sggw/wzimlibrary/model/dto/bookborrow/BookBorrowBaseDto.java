package pl.sggw.wzimlibrary.model.dto.bookborrow;

import lombok.Data;

@Data
public class BookBorrowBaseDto {

    protected Integer userId;
    protected String bookSlug;
    protected String title;

}
