package pl.sggw.wzimlibrary.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import pl.sggw.wzimlibrary.model.base.BookBorrowBase;
import pl.sggw.wzimlibrary.model.id.BookBorrowId;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "book_borrows")
@Getter
@Setter
public class BookBorrow extends BookBorrowBase {

    private final LocalDate borrowDate;
    private final LocalDate returnDate;

    public BookBorrow(@NonNull BookBorrowId id, LocalDate borrowDate, LocalDate returnDate) {
        super(id);
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public BookBorrow() {
        super(new BookBorrowId());
        this.borrowDate = LocalDate.now();
        this.returnDate = LocalDate.now().plusDays(30);
    }
}
