package pl.sggw.wzimlibrary.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import pl.sggw.wzimlibrary.model.base.BookBorrowBase;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "book_borrows")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class BookBorrow extends BookBorrowBase {

    private LocalDate borrowDate;
    private LocalDate returnDate;


}
