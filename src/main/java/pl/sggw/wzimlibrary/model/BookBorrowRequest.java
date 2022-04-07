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
@Table(name = "book_borrows_requests")
@Getter
@Setter
public class BookBorrowRequest extends BookBorrowBase {

    private LocalDate requestDate;

    public BookBorrowRequest(@NonNull BookBorrowId id, LocalDate requestDate) {
        super(id);
        this.requestDate = requestDate;
    }

    public BookBorrowRequest() {
        this(new BookBorrowId(), LocalDate.now());
    }
}
