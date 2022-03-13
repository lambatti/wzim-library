package pl.sggw.wzimlibrary.adapter;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.sggw.wzimlibrary.model.User;
import pl.sggw.wzimlibrary.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public interface SqlUserRepository extends UserRepository, JpaRepository<User, Integer> {

    @Override
    List<User> findAll();

    Optional<User> findByEmail(String email);
}
