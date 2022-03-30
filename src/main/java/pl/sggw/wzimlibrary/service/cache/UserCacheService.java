package pl.sggw.wzimlibrary.service.cache;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import pl.sggw.wzimlibrary.adapter.SqlUserRepository;
import pl.sggw.wzimlibrary.model.User;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserCacheService {

    private final SqlUserRepository userRepository;

    @Caching(evict = {
            @CacheEvict(value = "userEmail", key = "#email"),
            @CacheEvict(value = "allUsers", allEntries = true)
    })
    public void setPassword(String email, String encodedPassword) {
        userRepository.setPassword(email, encodedPassword);
    }

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

    @Cacheable(value = "userExistsByEmail", key = "#email")
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    // when deleting
    // @CacheEvict(value = "userExistsByEmail", key = "#email")
    // @CacheEvict(value = "userEmail", key = "#user.email")
    // @CacheEvict(value = "allUsers", allEntries = true)
}
