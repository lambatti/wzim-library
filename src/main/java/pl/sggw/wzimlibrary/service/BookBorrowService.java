package pl.sggw.wzimlibrary.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sggw.wzimlibrary.adapter.SqlBookBorrowProlongationRequestRepository;
import pl.sggw.wzimlibrary.adapter.SqlBookBorrowRepository;
import pl.sggw.wzimlibrary.adapter.SqlBookBorrowRequestRepository;
import pl.sggw.wzimlibrary.exception.BookBorrowConflictException;
import pl.sggw.wzimlibrary.exception.UserNotFoundException;
import pl.sggw.wzimlibrary.model.BookBorrow;
import pl.sggw.wzimlibrary.model.BookBorrowProlongationRequest;
import pl.sggw.wzimlibrary.model.BookBorrowRequest;
import pl.sggw.wzimlibrary.model.User;
import pl.sggw.wzimlibrary.model.constant.BookBorrowConstant;
import pl.sggw.wzimlibrary.model.constant.SchedulingConstant;
import pl.sggw.wzimlibrary.model.dto.bookborrow.BookBorrowDto;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookBorrowService {

    private final SqlBookBorrowRepository bookBorrowRepository;
    private final SqlBookBorrowRequestRepository bookBorrowRequestRepository;
    private final SqlBookBorrowProlongationRequestRepository bookBorrowProlongationRequestRepository;

    private final UserService userService;

    @Async
    public CompletableFuture<BookBorrowRequest> save(BookBorrowRequest bookBorrowRequest) {
        return CompletableFuture.completedFuture(bookBorrowRequestRepository.save(bookBorrowRequest));
    }

    @Async
    public CompletableFuture<BookBorrow> save(BookBorrow bookBorrow) {
        return CompletableFuture.completedFuture(bookBorrowRepository.save(bookBorrow));
    }

    @Async
    public CompletableFuture<Boolean> requestExistsByUserIdAndBookSlug(Integer userId, String bookSlug) {
        return CompletableFuture.completedFuture(bookBorrowRequestRepository.existsByUser_IdAndBookSlug(userId, bookSlug));
    }

    @Async
    public CompletableFuture<BookBorrowRequest> getByUserIdAndBookSlug(Integer userId, String bookSlug) {
        return CompletableFuture.completedFuture(bookBorrowRequestRepository.getByUser_IdAndBookSlug(userId, bookSlug));
    }

    @Async
    public CompletableFuture<Boolean> prolongationRequestExistsByUserIdAndBookSlug(Integer userId, String bookSlug) {
        return CompletableFuture.completedFuture(bookBorrowProlongationRequestRepository.existsByUser_IdAndBookSlug(userId, bookSlug));
    }

    @Async
    public CompletableFuture<Integer> deleteBookBorrowsFromUserByReturnDate(Integer userId, Date currentDate) {
        return CompletableFuture.completedFuture(bookBorrowRepository.deleteAllFromUserByReturnDate(userId, currentDate));
    }

    @Transactional
    public BookBorrowRequest addBookBorrowRequest(UserDetails userDetails, String bookSlug)
            throws UserNotFoundException, BookBorrowConflictException, ExecutionException, InterruptedException {

        User user = getUserFromUserDetails(userDetails);

        checkIfUserSentTheRequest(user, bookSlug);

        if (getBookBorrowByBookSlug(user, bookSlug).isPresent()) {
            throw new BookBorrowConflictException("User: " + user.getEmail()
                    + " has the book already: " + bookSlug);
        }

        BookBorrowRequest bookBorrowRequest = BookBorrowRequest.builder()
                .user(user).bookSlug(bookSlug).requestDate(BookBorrowConstant.CURRENT_DATE).build();

        userService.addBookBorrowRequestToUser(user, bookBorrowRequest).get();

        return bookBorrowRequest;

    }

    public void rejectBookBorrowRequest(BookBorrowDto bookBorrowDto)
            throws UserNotFoundException, BookBorrowConflictException, ExecutionException, InterruptedException {

        BookBorrowRequest request = getBookBorrowRequest(bookBorrowDto);

        deleteBookBorrowRequest(request);

    }

    public void acceptBookBorrowRequest(BookBorrowDto bookBorrowDto)
            throws UserNotFoundException, BookBorrowConflictException, ExecutionException, InterruptedException {

        BookBorrowRequest request = getBookBorrowRequest(bookBorrowDto);

        User user = request.getUser();

        BookBorrow bookBorrow = BookBorrow.builder().user(user)
                .bookSlug(request.getBookSlug())
                .borrowDate(BookBorrowConstant.CURRENT_DATE)
                .returnDate(BookBorrowConstant.RETURN_DATE)
                .build();

        addBookBorrowToUser(user, request, bookBorrow);

    }

    @Transactional
    public BookBorrowProlongationRequest addBookBorrowProlongationRequest(UserDetails userDetails, String bookSlug)
            throws UserNotFoundException, BookBorrowConflictException, ExecutionException, InterruptedException {

        User user = getUserFromUserDetails(userDetails);

        if (prolongationRequestExistsByUserIdAndBookSlug(user.getId(), bookSlug).get()) {
            throw new BookBorrowConflictException("User: " + user.getEmail() + " has already sent a prolongation " +
                    "request for the book: " + bookSlug);
        }

        Optional<BookBorrow> bookBorrow = getBookBorrowByBookSlug(user, bookSlug);

        if (bookBorrow.isEmpty()) {
            throw new BookBorrowConflictException("User: " + user.getEmail() + " has not borrowed the book: " + bookSlug);
        }

        // TODO: 17.04.2022 check if the book exists

        BookBorrowProlongationRequest request = BookBorrowProlongationRequest.builder()
                .user(user).bookSlug(bookSlug).requestDate(BookBorrowConstant.CURRENT_DATE)
                .prolongationDate(bookBorrow.get().getReturnDate().plusDays(BookBorrowConstant.BOOK_BORROW_DAYS))
                .build();

        userService.addBookBorrowProlongationRequestToUser(user, request).get();

        return request;
    }

    @Transactional
    void addBookBorrowToUser(User user, BookBorrowRequest request, BookBorrow bookBorrow)
            throws ExecutionException, InterruptedException {
        userService.addBookBorrowToUser(user, request, bookBorrow).get();
    }


    @Transactional
    BookBorrowRequest getBookBorrowRequest(BookBorrowDto bookBorrowDto)
            throws UserNotFoundException, ExecutionException, InterruptedException, BookBorrowConflictException {

        String email = bookBorrowDto.getEmail();
        String bookSlug = bookBorrowDto.getBookSlug();

        User user = userService.findByEmail(email).get().orElseThrow(() -> new UserNotFoundException("User with email: " + bookBorrowDto.getEmail() + " not found"));

        // TODO: 08.04.2022 check if the book exists

        Integer userId = user.getId();

        if (!requestExistsByUserIdAndBookSlug(userId, bookSlug).get()) {
            throw new BookBorrowConflictException("There is no such request with the email: "
                    + email + " and the book: " + bookSlug);
        }

        return getByUserIdAndBookSlug(userId, bookSlug).get();
    }

    void deleteBookBorrowRequest(BookBorrowRequest bookBorrowRequest) throws ExecutionException, InterruptedException {

        User user = bookBorrowRequest.getUser();

        userService.removeBookBorrowRequestFromUser(user, bookBorrowRequest).get();

    }

    private void checkIfUserSentTheRequest(User user, String bookSlug) throws BookBorrowConflictException {

        for (var request : user.getBookBorrowRequests()) {
            if (request.getBookSlug().equals(bookSlug)) {
                throw new BookBorrowConflictException("User: " + user.getEmail()
                        + " already has sent the request for the book: " + bookSlug);
            }
        }
    }

    private Optional<BookBorrow> getBookBorrowByBookSlug(User user, String bookSlug) {

        return user.getBookBorrows().stream().filter(bookBorrow -> bookBorrow.getBookSlug().equals(bookSlug)).findAny();
    }

    @Scheduled(cron = SchedulingConstant.EVERY_DAY_AT_MIDNIGHT, zone = SchedulingConstant.TIMEZONE)
    @Transactional
    public void returnBooksFromAllUsers() throws ExecutionException, InterruptedException {

        List<User> allUsers = userService.findAll().get();

        Date currentDate = Date.valueOf(BookBorrowConstant.CURRENT_DATE);

        for (User user : allUsers) {
            int readBooks = deleteBookBorrowsFromUserByReturnDate(user.getId(), currentDate).get();

            userService.updateReadBooksByUser(user, readBooks).get();
        }
    }

    private User getUserFromUserDetails(UserDetails userDetails) throws UserNotFoundException {
        return userService.getUserFromUserDetails(userDetails);
    }
}
