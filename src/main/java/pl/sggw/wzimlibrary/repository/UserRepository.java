package pl.sggw.wzimlibrary.repository;

import pl.sggw.wzimlibrary.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> findAll();

    Optional<User> findByEmail(String email);

    <S extends User> S save(S s);

    boolean existsByEmail(String email);
}
