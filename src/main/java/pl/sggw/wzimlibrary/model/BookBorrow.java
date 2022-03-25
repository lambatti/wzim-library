package pl.sggw.wzimlibrary.model;

import lombok.Getter;
import lombok.Setter;
import pl.sggw.wzimlibrary.model.id.BookBorrowId;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "book_borrows")
@Getter
@Setter
public class BookBorrow {

    @EmbeddedId
    private BookBorrowId id;

    private LocalDate borrowDate;
    private LocalDate returnDate;

    // Additional getters and setters for composite primary key columns

    public User getUser() {
        return id.getUser();
    }

    public void setUser(User user) {
        this.id.setUser(user);
    }

    public String getBookSlug() {
        return id.getBookSlug();
    }

    public void setBookSlug(String bookSlug) {
        this.id.setBookSlug(bookSlug);
    }
}
