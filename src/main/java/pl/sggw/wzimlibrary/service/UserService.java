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
import pl.sggw.wzimlibrary.exception.PasswordMissmatchException;
import pl.sggw.wzimlibrary.exception.SecurityQuestionAnswerMissmatchException;
import pl.sggw.wzimlibrary.exception.UserAlreadyExistsException;
import pl.sggw.wzimlibrary.exception.UserNotFoundException;
import pl.sggw.wzimlibrary.model.*;
import pl.sggw.wzimlibrary.model.constant.Role;
import pl.sggw.wzimlibrary.model.constant.SecurityQuestion;
import pl.sggw.wzimlibrary.model.dto.user.UserPanelChangePasswordDto;
import pl.sggw.wzimlibrary.model.dto.user.UserPanelChangeQuestionDto;
import pl.sggw.wzimlibrary.model.dto.user.UserRegistrationDto;
import pl.sggw.wzimlibrary.model.dto.user.UserForgottenPasswordDto;
import pl.sggw.wzimlibrary.service.cache.UserCacheService;
import pl.sggw.wzimlibrary.util.JwtUtil;

import javax.persistence.criteria.CriteriaBuilder;
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
        return CompletableFuture.runAsync(() -> userCacheService.setPassword(email, encodedPassword));
    }

    public CompletableFuture<Void> setQuestionAndAnswer(String email, String question, String answer) {
        return CompletableFuture.runAsync(() -> userCacheService.setQuestionAndAnswer(email, question, answer));
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

    public void changePassword(UserDetails userDetails, UserPanelChangePasswordDto userPanelChangePasswordDto) throws UserNotFoundException, PasswordMissmatchException {
        if (!userPanelChangePasswordDto.getNewPassword().equals(userPanelChangePasswordDto.getNewPasswordConfirmation())) {
            throw new PasswordMissmatchException("Password confirmation is not matching");
        }
        User user = getUserFromUserDetails(userDetails);

        if (!doesThePasswordMatch(userPanelChangePasswordDto.getOldPassword(), user.getPassword())) {
            throw new PasswordMissmatchException("New password and old password does not match");
        }
        setUserPassword(user, userPanelChangePasswordDto.getNewPassword());
    }

    private void setUserPassword(User user, String newPassword) {
        String encodedPassword = passwordEncoder.encode(newPassword);
        setPassword(user.getEmail(), encodedPassword);
    }

    private boolean doesThePasswordMatch(String newPassword, String oldPassword) {
        return passwordEncoder.matches(newPassword, oldPassword);
    }

    public void forgottenPasswordChange(UserForgottenPasswordDto userForgottenPasswordDto) throws ExecutionException, InterruptedException, PasswordMissmatchException, SecurityQuestionAnswerMissmatchException {
        if (!userForgottenPasswordDto.getNewPassword().equals(userForgottenPasswordDto.getNewPasswordConfirmation())) {
            throw new PasswordMissmatchException("Password confirmation is not matching");
        }

        Optional<User> user = findByEmail(userForgottenPasswordDto.getEmail()).get();

        if (user.isEmpty() ||
                !doesTheQuestionMatch(userForgottenPasswordDto.getQuestion().toString(), user.get().getSecurityQuestion().toString()) ||
                !doesTheAnswerMatch(userForgottenPasswordDto.getAnswer(), user.get().getSecurityQuestionAnswer())) {
            throw new SecurityQuestionAnswerMissmatchException("Security question and provided answer are not matching");
        }

        if (doesThePasswordMatch(userForgottenPasswordDto.getNewPassword(), user.get().getPassword())) {
            throw new PasswordMissmatchException("New password and old password does not match");
        }

        setUserPassword(user.get(), userForgottenPasswordDto.getNewPassword());
    }

    private boolean doesTheQuestionMatch(String sentQuestion, String userQuestion) {
        return sentQuestion.equals(userQuestion);
    }

    private boolean doesTheAnswerMatch(String sentAnswer, String userAnswer) {
        return sentAnswer.equals(userAnswer);
    }

    public void changeQuestion(UserDetails userDetails, UserPanelChangeQuestionDto userPanelChangeQuestionDto) throws ExecutionException, InterruptedException, PasswordMissmatchException, SecurityQuestionAnswerMissmatchException, UserNotFoundException {
        User user = getUserFromUserDetails(userDetails);

        if (!doesThePasswordMatch(userPanelChangeQuestionDto.getPassword(), user.getPassword())) {
            throw new PasswordMissmatchException("User provided wrong password");
        }
        setQuestionAndAnswer(user.getEmail(), userPanelChangeQuestionDto.getSecurityQuestion().name(), userPanelChangeQuestionDto.getSecurityQuestionAnswer());
    }


}
