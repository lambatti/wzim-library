package pl.sggw.wzimlibrary.model.id;

import lombok.*;
import pl.sggw.wzimlibrary.model.User;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BookBorrowId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, updatable = false)
    private String bookSlug;

}
