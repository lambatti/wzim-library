package pl.sggw.wzimlibrary.adapter;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import pl.sggw.wzimlibrary.model.User;
import pl.sggw.wzimlibrary.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public interface SqlUserRepository extends UserRepository, JpaRepository<User, Integer> {

    @Override
    List<User> findAll();

    @Override
    Optional<User> findByEmail(String email);

    @Override
    <S extends User> S save(S s);

    @Override
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE users SET password=:newPassword WHERE email=:email")
    void setPassword(@Param("email") String email, @Param("newPassword") String newPassword);

    @Override
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE users SET SECURITY_QUESTION=:newQuestion, SECURITY_QUESTION_ANSWER=:newAnswer WHERE email=:email")
    void setQuestionAndAnswer(@Param("email") String email, @Param("newQuestion") String question, @Param("newAnswer") String answer);

    @Override
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE users SET ROLE=:newRole WHERE email=:email")
    void setRole(@Param("email") String email, @Param("newRole") String role);

    boolean existsByEmail(String email);
}
