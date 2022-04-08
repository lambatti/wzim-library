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

    private final LocalDate borrowDate;
    private final LocalDate returnDate;

    public BookBorrow(User user, String bookSlug, LocalDate borrowDate, LocalDate returnDate) {
        super(user, bookSlug);
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public BookBorrow() {
        super(new User(), "");
        this.borrowDate = LocalDate.now();
        this.returnDate = LocalDate.now().plusDays(30);
    }
}
