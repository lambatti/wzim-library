package pl.sggw.wzimlibrary.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import pl.sggw.wzimlibrary.model.constant.Gender;
import pl.sggw.wzimlibrary.model.constant.Role;
import pl.sggw.wzimlibrary.model.constant.SecurityQuestion;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private SecurityQuestion securityQuestion;

    private String securityQuestionAnswer;

    @Enumerated(EnumType.STRING)
    private Role role;

    @JsonIgnore
    @OneToMany(mappedBy = "id.user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BookBorrow> bookBorrows;

    @JsonIgnore
    @OneToMany(mappedBy = "id.user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BookBorrowRequest> bookBorrowRequests;

    @JsonIgnore
    @OneToMany(mappedBy = "id.user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BookBorrowProlongationRequest> bookBorrowProlongationRequests;

}
