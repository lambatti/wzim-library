package pl.sggw.wzimlibrary.repository;

import pl.sggw.wzimlibrary.model.User;
import pl.sggw.wzimlibrary.model.constant.Role;
import pl.sggw.wzimlibrary.model.constant.SecurityQuestion;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> findAll();

    Optional<User> findByEmail(String email);

    <S extends User> S save(S s);

    void setPassword(String email, String newPassword);

    void setQuestionAndAnswer(String email, String question, String answer);

    void changeRole(String email, String role);

    boolean existsByEmail(String email);
}
