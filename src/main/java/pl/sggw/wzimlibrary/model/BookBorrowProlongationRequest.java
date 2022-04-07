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
@Table(name = "book_borrows_prolongation_requests")
@Getter
@Setter
public class BookBorrowProlongationRequest extends BookBorrowBase {

    private LocalDate requestDate;
    private LocalDate prolongationDate;

    public BookBorrowProlongationRequest(@NonNull BookBorrowId id, LocalDate requestDate, LocalDate prolongationDate) {
        super(id);
        this.requestDate = requestDate;
        this.prolongationDate = prolongationDate;
    }

    public BookBorrowProlongationRequest() {
        super(new BookBorrowId());
    }
}
