package pl.sggw.wzimlibrary.model.base;

import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.sggw.wzimlibrary.model.User;

import javax.persistence.*;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public abstract class BookBorrowBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String bookSlug;

}
