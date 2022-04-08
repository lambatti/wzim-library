package pl.sggw.wzimlibrary.model;

import lombok.Getter;
import lombok.Setter;
import pl.sggw.wzimlibrary.model.base.BookBorrowBase;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "book_borrows_prolongation_requests")
@Getter
@Setter
public class BookBorrowProlongationRequest extends BookBorrowBase {

    private LocalDate requestDate;
    private LocalDate prolongationDate;

    public BookBorrowProlongationRequest(User user, String bookSlug, LocalDate requestDate, LocalDate prolongationDate) {
        super(user, bookSlug);
        this.requestDate = requestDate;
        this.prolongationDate = prolongationDate;
    }

    public BookBorrowProlongationRequest() {
        super(new User(), "");
        this.requestDate = LocalDate.now();
        this.prolongationDate = LocalDate.now();
    }
}
