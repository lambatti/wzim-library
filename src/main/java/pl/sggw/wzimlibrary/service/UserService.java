package pl.sggw.wzimlibrary.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
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
import pl.sggw.wzimlibrary.adapter.SqlBookBorrowRepository;
import pl.sggw.wzimlibrary.exception.*;
import pl.sggw.wzimlibrary.model.*;
import pl.sggw.wzimlibrary.model.constant.Role;
import pl.sggw.wzimlibrary.model.dto.bookborrow.BookBorrowDto;
import pl.sggw.wzimlibrary.model.dto.user.*;
import pl.sggw.wzimlibrary.repository.BookBorrowRepository;
import pl.sggw.wzimlibrary.service.cache.UserCacheService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Log
public class UserService implements UserDetailsService {

    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserCacheService userCacheService;
    private SqlBookBorrowRepository bookBorrowRepository;
    private BookService bookService;

    @Async
    public CompletableFuture<Optional<User>> findByEmail(String email) {
        return CompletableFuture.completedFuture(userCacheService.findByEmail(email));
    }


    @Async
    public CompletableFuture<List<User>> findAll() {
        return CompletableFuture.completedFuture(userCacheService.findAll());
    }

    @Async
    public CompletableFuture<List<User>> findAllWorkers() {
        return CompletableFuture.completedFuture(userCacheService.findAllWorkers());
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

    public CompletableFuture<Void> changeRole(String email, String role) {
        return CompletableFuture.runAsync(() -> userCacheService.changeRole(email, role));
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

    public UserPanelSummaryDto getUserSummary(UserDetails userDetails) throws UserNotFoundException {
        User user = getUserFromUserDetails(userDetails);
        return modelMapper.map(user, UserPanelSummaryDto.class);
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

    public void changePassword(UserDetails userDetails, UserPanelChangePasswordDto userPanelChangePasswordDto) throws UserNotFoundException, PasswordMismatchException, ExecutionException, InterruptedException {
        if (!userPanelChangePasswordDto.getNewPassword().equals(userPanelChangePasswordDto.getNewPasswordConfirmation())) {
            throw new PasswordMismatchException("Password confirmation is not matching");
        }
        User user = getUserFromUserDetails(userDetails);

        if (!doesThePasswordMatch(userPanelChangePasswordDto.getOldPassword(), user.getPassword())) {
            throw new PasswordMismatchException("New password and old password does not match");
        }
        setUserPassword(user, userPanelChangePasswordDto.getNewPassword());
    }

    private void setUserPassword(User user, String newPassword) throws ExecutionException, InterruptedException {
        String encodedPassword = passwordEncoder.encode(newPassword);
        setPassword(user.getEmail(), encodedPassword).get();
    }

    private boolean doesThePasswordMatch(String newPassword, String oldPassword) {
        return passwordEncoder.matches(newPassword, oldPassword);
    }

    public void changeForgottenPassword(UserForgottenPasswordDto userForgottenPasswordDto) throws ExecutionException, InterruptedException, PasswordMismatchException, SecurityQuestionAnswerMismatchException {
        if (!userForgottenPasswordDto.getNewPassword().equals(userForgottenPasswordDto.getNewPasswordConfirmation())) {
            throw new PasswordMismatchException("Password confirmation is not matching");
        }
        User user = findByEmail(userForgottenPasswordDto.getEmail()).get()
                .orElseThrow(() -> new UsernameNotFoundException("User with this email not found"));

        if (!doesTheQuestionMatch(userForgottenPasswordDto.getQuestion().toString(), user.getSecurityQuestion().toString()) ||
                !doesTheAnswerMatch(userForgottenPasswordDto.getAnswer(), user.getSecurityQuestionAnswer())) {
            throw new SecurityQuestionAnswerMismatchException("Security question and provided answer are not matching");
        }

        if (doesThePasswordMatch(userForgottenPasswordDto.getNewPassword(), user.getPassword())) {
            throw new PasswordMismatchException("New password and old password are the same");
        }
        setUserPassword(user, userForgottenPasswordDto.getNewPassword());
    }

    private boolean doesTheQuestionMatch(String sentQuestion, String userQuestion) {
        return sentQuestion.equals(userQuestion);
    }

    private boolean doesTheAnswerMatch(String sentAnswer, String userAnswer) {
        return sentAnswer.equals(userAnswer);
    }

    public void changeQuestion(UserDetails userDetails, UserPanelChangeQuestionDto userPanelChangeQuestionDto) throws PasswordMismatchException, UserNotFoundException, ExecutionException, InterruptedException {
        User user = getUserFromUserDetails(userDetails);

        if (!doesThePasswordMatch(userPanelChangeQuestionDto.getPassword(), user.getPassword())) {
            throw new PasswordMismatchException("User provided wrong password");
        }
        setQuestionAndAnswer(user.getEmail(), userPanelChangeQuestionDto.getSecurityQuestion().name(), userPanelChangeQuestionDto.getSecurityQuestionAnswer()).get();
    }

    public void promoteWorker(UserWorkerPromotionDto userWorkerPromotionDto) throws WrongRoleException, ExecutionException, InterruptedException {
        User user = findByEmail(userWorkerPromotionDto.getEmail()).get()
                .orElseThrow(() -> new UsernameNotFoundException("User with this email not found"));

        if (!user.getRole().toString().equals("USER")) {
            throw new WrongRoleException("User don't have role: USER");
        }
        changeRole(user.getEmail(), "WORKER");
    }

    public void demoteWorker(UserWorkerPromotionDto userWorkerPromotionDto) throws WrongRoleException, ExecutionException, InterruptedException {
        User user = findByEmail(userWorkerPromotionDto.getEmail()).get()
                .orElseThrow(() -> new UsernameNotFoundException("User with this email not found"));

        if (!user.getRole().toString().equals("WORKER")) {
            throw new WrongRoleException("User don't have role: WORKER");
        }
        changeRole(user.getEmail(), "USER");
    }

    public List<UserBorrowedBooksDto> borrowedBooks(UserDetails userDetails) throws UserNotFoundException, ExecutionException, InterruptedException {
        User user = getUserFromUserDetails(userDetails);
        List<String> borrowedBookSlugList = getSlugFromBookBorrowDto(user.getId());

        log.info(borrowedBookSlugList.toString());

        List<Book> userBookList = borrowedBookSlugList.stream()
                .map(slug -> bookService.getBookBySlug(slug))
                .collect(Collectors.toList());

        List<UserBorrowedBooksDto> userBorrowedBooksDtoList = userBookList.stream()
                .map(book -> modelMapper.map(book, UserBorrowedBooksDto.class))
                .collect(Collectors.toList());

        for (String slug : borrowedBookSlugList) {
            userBorrowedBooksDtoList.forEach(userBorrowedBooksDto -> userBorrowedBooksDto.setBookSlug(slug));
        }

        return userBorrowedBooksDtoList;
    }

        private List<String> getSlugFromBookBorrowDto(Integer id) throws ExecutionException, InterruptedException {
        return findAllBookBorrowsByUserId(id).get()
                .stream()
                .map(bookBorrow -> modelMapper.map(bookBorrow.getBookSlug(), String.class))
                .collect(Collectors.toList());
    }
    @Async
    public CompletableFuture<List<BookBorrow>> findAllBookBorrowsByUserId(Integer userId) {
        return CompletableFuture.completedFuture(bookBorrowRepository.findAllByUser_Id(userId));
    }

    public List<WorkerDto> getAllWorkers() throws ExecutionException, InterruptedException {
        return findAllWorkers().get()
                .stream()
                .map(user -> modelMapper.map(user, WorkerDto.class))
                .collect(Collectors.toList());
    }
}
