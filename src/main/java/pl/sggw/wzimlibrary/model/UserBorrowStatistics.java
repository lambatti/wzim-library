package pl.sggw.wzimlibrary.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users_borrow_statistics")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserBorrowStatistics {

    @Id
    @Column(name = "user_id")
    private Integer id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    private int borrowedBooks;
    private int readBooks;
}
