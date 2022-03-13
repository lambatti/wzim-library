package pl.sggw.wzimlibrary.repository;

import pl.sggw.wzimlibrary.model.User;

import java.util.List;

public interface UserRepository {
    List<User> findAll();
}
