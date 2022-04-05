package pl.sggw.wzimlibrary.model;

import lombok.Getter;
import lombok.Setter;
import pl.sggw.wzimlibrary.model.base.BookBorrowBase;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "book_borrows")
@Getter
@Setter
public class BookBorrow extends BookBorrowBase {

    private LocalDate borrowDate;
    private LocalDate returnDate;

}
