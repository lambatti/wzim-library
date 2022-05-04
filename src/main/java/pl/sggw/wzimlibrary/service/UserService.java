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
import pl.sggw.wzimlibrary.model.*;
import pl.sggw.wzimlibrary.model.constant.Role;
import pl.sggw.wzimlibrary.model.dto.user.UserPanelChangePasswordDto;
import pl.sggw.wzimlibrary.model.dto.user.UserRegistrationDto;
import pl.sggw.wzimlibrary.service.cache.UserCacheService;
import pl.sggw.wzimlibrary.util.JwtUtil;

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
    private final JwtUtil jwtUtil;

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
    public CompletableFuture<Void> setPassword(String email, String encodedPassword) {
//        return CompletableFuture.completedFuture(userCacheService.setPassword(email, encodedPassword)).complete(null);
//        return CompletableFuture.completedFuture(null);
        userCacheService.setPassword(email, encodedPassword);
        return null;
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
    public CompletableFuture<Void> addBookBorrowProlongationRequestToUser(User user, BookBorrowProlongationRequest request) {
        return CompletableFuture.runAsync(() -> userCacheService.addBookBorrowProlongationRequestToUser(user, request));
    }

    @Async
    public CompletableFuture<Void> removeBookBorrowRequestFromUser(User user, BookBorrowRequest request) {
        return CompletableFuture.runAsync(() -> userCacheService.removeBookBorrowRequestFromUser(user, request));
    }

    @Async
    public CompletableFuture<Void> removeBookBorrowProlongationRequestFromUser(User user, BookBorrowProlongationRequest request) {
        return CompletableFuture.runAsync(() -> userCacheService.removeBookBorrowProlongationRequestFromUser(user, request));
    }

    @Async
    public CompletableFuture<Void> updateBookBorrowReturnDate(BookBorrow bookBorrow) {
        return CompletableFuture.runAsync(() -> userCacheService.updateBookBorrowReturnDate(bookBorrow.getUser(), bookBorrow));
    }

    @Async
    public CompletableFuture<Void> updateReadBooksByUser(User user, int booksCount) {
        return CompletableFuture.runAsync(() -> userCacheService.updateReadBooksByUser(user, booksCount));
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

        User createdUser = modelMapper.map(userRegistrationDto, User.class);

        createdUser.setBorrowStatistics(createUserBorrowStatistics(createdUser));

        return save(createdUser).get();
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

    private UserBorrowStatistics createUserBorrowStatistics(User user) {
        return new UserBorrowStatistics(user.getId(), user, 0, 0);
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

    public boolean changePassword(String token, UserPanelChangePasswordDto userPanelChangePasswordDto) throws ExecutionException, InterruptedException {
        if (!userPanelChangePasswordDto.getNewPassword().equals(userPanelChangePasswordDto.getNewPasswordConfirmation())) {
            return false;
        }

        Optional<User> user = getUserByToken(token);

        if (user.isEmpty() || !doesThePasswordMatch(userPanelChangePasswordDto.getOldPassword(), user.get().getPassword())) {
            return false;
        }

        setUserPassword(user.get(), userPanelChangePasswordDto.getNewPassword());
        return true;
    }

    private void setUserPassword(User user, String newPassword) {
        String encodedPassword = passwordEncoder.encode(newPassword);
        //userCacheService.setPassword(user.getEmail(), encodedPassword);
        setPassword(user.getEmail(), encodedPassword);
    }

    private boolean doesThePasswordMatch(String oldPassword, String newPassword) {
        return passwordEncoder.matches(oldPassword, newPassword);
    }

    private Optional<User> getUserByToken(String token) throws ExecutionException, InterruptedException {
        String email = extractEmailFromToken(token);
        return findByEmail(email).get();
    }

    public String extractEmailFromToken(String token) {
        token = jwtUtil.removeBearer(token);
        return jwtUtil.extractEmail(token);
    }
}
