package pl.sggw.wzimlibrary.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sggw.wzimlibrary.exception.UserAlreadyExistsException;
import pl.sggw.wzimlibrary.exception.UserNotFoundException;
import pl.sggw.wzimlibrary.model.BookBorrow;
import pl.sggw.wzimlibrary.model.BookBorrowRequest;
import pl.sggw.wzimlibrary.model.User;
import pl.sggw.wzimlibrary.model.constant.Role;
import pl.sggw.wzimlibrary.model.dto.user.UserRegistrationDto;
import pl.sggw.wzimlibrary.service.cache.UserCacheService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {

    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserCacheService userCacheService;

    @Async
    public CompletableFuture<Optional<User>> findByEmail(String email) {
        return CompletableFuture.completedFuture(userCacheService.findByEmail(email));
    }

    @Async
    public CompletableFuture<List<User>> findAll() {
        return CompletableFuture.completedFuture(userCacheService.findAll());
    }

    @Async
    public CompletableFuture<User> save(User user) {
        return CompletableFuture.completedFuture(userCacheService.save(user));
    }

    @Async
    public CompletableFuture<Boolean> existsByEmail(String email) {
        return CompletableFuture.completedFuture(userCacheService.existsByEmail(email));
    }

    @Async
    public CompletableFuture<Void> addBookBorrowRequestToUser(User user, BookBorrowRequest request) {
        return CompletableFuture.runAsync(() -> userCacheService.addBookBorrowRequestToUser(user, request));
    }

    @Async
    public CompletableFuture<Void> addBookBorrowToUser(User user, BookBorrowRequest request, BookBorrow bookBorrow) {
        return CompletableFuture.runAsync(() -> userCacheService.addBookBorrowToUser(user, request, bookBorrow));
    }

    @Async
    public CompletableFuture<Void> removeBookBorrowRequestFromUser(User user, BookBorrowRequest request) {
        return CompletableFuture.runAsync(() -> userCacheService.removeBookBorrowRequestFromUser(user, request));
    }

    @Transactional
    public User registerUser(UserRegistrationDto userRegistrationDto)
            throws ExecutionException, InterruptedException, UserAlreadyExistsException {

        if (existsByEmail(userRegistrationDto.getEmail()).get()) {
            throw new UserAlreadyExistsException(
                    "User with the email: " + userRegistrationDto.getEmail() + " already exists");
        }

        String encodedPassword = passwordEncoder.encode(userRegistrationDto.getPassword());

        userRegistrationDto.setPassword(encodedPassword);

        return save(modelMapper.map(userRegistrationDto, User.class)).get();
    }

    public User getUserFromUserDetails(UserDetails userDetails) throws UserNotFoundException {

        String email = "";
        Optional<User> user = Optional.empty();

        try {
            email = userDetails.getUsername();
            user = findByEmail(email).get();

        } catch (NullPointerException e) {
            throw new UsernameNotFoundException("User not found.");

        } catch (ExecutionException | InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        String finalEmail = email;

        return Optional.ofNullable(user)
                .orElseThrow(() -> new UserNotFoundException("User with the email " + finalEmail + " does not exist."))
                .get();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> tempUser = Optional.empty();

        try {
            tempUser = findByEmail(email).get();
        } catch (InterruptedException | ExecutionException e) {
            Thread.currentThread().interrupt();
        }

        User user = tempUser.orElseThrow(() -> new UsernameNotFoundException(
                "User with the email: " + email + " not found.")
        );

        List<GrantedAuthority> authorities = generateAuthorities(user);

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(),
                authorities);
    }

    private List<GrantedAuthority> generateAuthorities(User user) {

        List<GrantedAuthority> authorities = new ArrayList<>();

        Role userRole = user.getRole();

        switch (userRole) {
            case ADMIN: {
                authorities.add(new SimpleGrantedAuthority(Role.ADMIN.toString()));
            }
            case WORKER: {
                authorities.add(new SimpleGrantedAuthority(Role.WORKER.toString()));
            }
            case USER: {
                authorities.add(new SimpleGrantedAuthority(Role.USER.toString()));
                break;
            }
        }

        return authorities;
    }
}
