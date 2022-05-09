package pl.sggw.wzimlibrary.service.cache;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import pl.sggw.wzimlibrary.adapter.SqlUserBorrowStatisticsRepository;
import pl.sggw.wzimlibrary.adapter.SqlUserRepository;
import pl.sggw.wzimlibrary.model.BookBorrow;
import pl.sggw.wzimlibrary.model.BookBorrowProlongationRequest;
import pl.sggw.wzimlibrary.model.BookBorrowRequest;
import pl.sggw.wzimlibrary.model.User;
import pl.sggw.wzimlibrary.model.constant.BookBorrowConstant;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserCacheService {

    private final SqlUserRepository userRepository;
    private final SqlUserBorrowStatisticsRepository userBorrowStatisticsRepository;

    @Caching(evict = {
            @CacheEvict(value = "userEmail", key = "#email"),
            @CacheEvict(value = "allUsers", allEntries = true)
    })
    public void setPassword(String email, String encodedPassword) {
        userRepository.setPassword(email, encodedPassword);
    }

    @Caching(evict = {
            @CacheEvict(value = "userEmail", key = "#email"),
            @CacheEvict(value = "allUsers", allEntries = true)
    })
    public void setQuestionAndAnswer(String email, String question, String answer) {
        userRepository.setQuestionAndAnswer(email, question, answer);
    }

    @Caching(evict = {
            @CacheEvict(value = "userEmail", key = "#email"),
            @CacheEvict(value = "allUsers", allEntries = true)
    })
    public void changeRole(String email, String role) {
        userRepository.setRole(email, role);
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

    @Caching(evict = {
            @CacheEvict(value = "allUsers", allEntries = true),
            @CacheEvict(value = "userEmail", key = "#user.email")
    })
    public void addBookBorrowRequestToUser(User user, BookBorrowRequest request) {
        user.getBookBorrowRequests().add(request);
        userRepository.save(user);
    }

    @Caching(evict = {
            @CacheEvict(value = "allUsers", allEntries = true),
            @CacheEvict(value = "userEmail", key = "#user.email")
    })
    public void addBookBorrowProlongationRequestToUser(User user, BookBorrowProlongationRequest request) {
        user.getBookBorrowProlongationRequests().add(request);
        userRepository.save(user);
    }

    @Caching(evict = {
            @CacheEvict(value = "allUsers", allEntries = true),
            @CacheEvict(value = "userEmail", key = "#user.email")
    })
    public void removeBookBorrowRequestFromUser(User user, BookBorrowRequest request) {
        user.getBookBorrowRequests().remove(request);
        userRepository.save(user);
    }

    @Caching(evict = {
            @CacheEvict(value = "allUsers", allEntries = true),
            @CacheEvict(value = "userEmail", key = "#user.email")
    })
    public void removeBookBorrowProlongationRequestFromUser(User user, BookBorrowProlongationRequest request) {
        user.getBookBorrowProlongationRequests().remove(request);
        userRepository.save(user);
    }

    @Caching(evict = {
            @CacheEvict(value = "allUsers", allEntries = true),
            @CacheEvict(value = "userEmail", key = "#user.email")
    })
    public void addBookBorrowToUser(User user, BookBorrowRequest request, BookBorrow bookBorrow) {
        user.getBookBorrows().add(bookBorrow);
        user.getBookBorrowRequests().remove(request);
        user.getBorrowStatistics().setBorrowedBooks(user.getBorrowStatistics().getBorrowedBooks() + 1);
        userRepository.save(user);
    }

    @Caching(evict = {
            @CacheEvict(value = "allUsers", allEntries = true),
            @CacheEvict(value = "userEmail", key = "#user.email")
    })
    public void updateReadBooksByUser(User user, int booksCount) {
        user.getBorrowStatistics().setReadBooks(user.getBorrowStatistics().getReadBooks() + booksCount);
        userRepository.save(user);
    }

    @Caching(evict = {
            @CacheEvict(value = "allUsers", allEntries = true),
            @CacheEvict(value = "userEmail", key = "#user.email")
    })
    public void updateBookBorrowReturnDate(User user, BookBorrow bookBorrow) {
        bookBorrow.setReturnDate(BookBorrowConstant.addBookBorrowDays(bookBorrow.getReturnDate()));
        userRepository.save(user);
    }

    // when deleting
    // @CacheEvict(value = "userExistsByEmail", key = "#email")
    // @CacheEvict(value = "userEmail", key = "#user.email")
    // @CacheEvict(value = "allUsers", allEntries = true)
}
