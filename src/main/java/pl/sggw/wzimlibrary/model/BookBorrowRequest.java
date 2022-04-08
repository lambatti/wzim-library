package pl.sggw.wzimlibrary.model;

import lombok.Getter;
import lombok.Setter;
import pl.sggw.wzimlibrary.model.base.BookBorrowBase;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "book_borrows_requests")
@Getter
@Setter
public class BookBorrowRequest extends BookBorrowBase {

    private LocalDate requestDate;

    public BookBorrowRequest(User user, String bookSlug, LocalDate requestDate) {
        super(user, bookSlug);
        this.requestDate = requestDate;
    }

    public BookBorrowRequest() {
        super(new User(), "");
        this.requestDate = LocalDate.now();
    }
}
