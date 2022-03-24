package pl.sggw.wzimlibrary.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode
public class BookBorrowId implements Serializable {

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "book_slug")
    private String bookSlug;

}
