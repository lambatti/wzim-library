package pl.sggw.wzimlibrary.service.cache;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pl.sggw.wzimlibrary.adapter.SqlUserRepository;
import pl.sggw.wzimlibrary.model.User;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserCacheService {

    private final SqlUserRepository userRepository;


    @Cacheable(value = "userEmail", key = "#email")
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Cacheable(value = "allUsers")
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @CacheEvict(value = "allUsers", allEntries = true)
    @CachePut(value = "userEmail", key = "#user.email")
    public User save(User user) {
        return userRepository.save(user);
    }

}
