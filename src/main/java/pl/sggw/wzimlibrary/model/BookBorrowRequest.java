package pl.sggw.wzimlibrary.model;

import lombok.Getter;
import lombok.Setter;
import pl.sggw.wzimlibrary.model.base.BookBorrowBase;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "book_borrows_requests")
@Getter
@Setter
public class BookBorrowRequest extends BookBorrowBase {

    private String requestDate;

}
