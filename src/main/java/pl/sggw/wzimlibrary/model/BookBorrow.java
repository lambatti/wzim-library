package pl.sggw.wzimlibrary.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "book_borrows")
@Getter
@Setter
public class BookBorrow {

    @EmbeddedId
    private BookBorrowId id;

    @ManyToOne
    @MapsId("userId")
    private User user;

    /*
    @MapsId("bookSlug")
    private String bookSlug;
    */

    private LocalDate borrowDate;
    private LocalDate returnDate;
}
