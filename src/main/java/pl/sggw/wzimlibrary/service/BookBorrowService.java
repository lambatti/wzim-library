package pl.sggw.wzimlibrary.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sggw.wzimlibrary.adapter.SqlBookBorrowProlongationRequestRepository;
import pl.sggw.wzimlibrary.adapter.SqlBookBorrowRepository;
import pl.sggw.wzimlibrary.adapter.SqlBookBorrowRequestRepository;
import pl.sggw.wzimlibrary.exception.BookBorrowConflictException;
import pl.sggw.wzimlibrary.exception.UserNotFoundException;
import pl.sggw.wzimlibrary.model.BookBorrow;
import pl.sggw.wzimlibrary.model.BookBorrowRequest;
import pl.sggw.wzimlibrary.model.User;
import pl.sggw.wzimlibrary.model.dto.bookborrow.BookBorrowDto;
import pl.sggw.wzimlibrary.service.cache.BookBorrowCacheService;

import java.time.LocalDate;
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

    private final BookBorrowCacheService bookBorrowCacheService;


    @Async
    public CompletableFuture<BookBorrowRequest> save(BookBorrowRequest bookBorrowRequest) {
        return CompletableFuture.completedFuture(bookBorrowRequestRepository.save(bookBorrowRequest));
    }

    @Async
    public CompletableFuture<BookBorrow> save(BookBorrow bookBorrow) {
        return CompletableFuture.completedFuture(bookBorrowRepository.save(bookBorrow));
    }

    @Async
    public CompletableFuture<Boolean> existsByUserIdAndBookSlug(Integer userId, String bookSlug) {
        return CompletableFuture.completedFuture(bookBorrowRequestRepository.existsByUserIdAndBookSlug(userId, bookSlug));
    }

    @Async
    public CompletableFuture<BookBorrowRequest> getByUserIdAndBookSlug(Integer userId, String bookSlug) {
        return CompletableFuture.completedFuture(bookBorrowRequestRepository.getByUserIdAndBookSlug(userId, bookSlug));
    }

    @Transactional
    public BookBorrowRequest addBookBorrowRequest(UserDetails userDetails, String bookSlug)
            throws UserNotFoundException, BookBorrowConflictException, ExecutionException, InterruptedException {

        User user = userService.getUserFromUserDetails(userDetails);

        checkIfUserSentTheRequest(user, bookSlug);

        checkIfUserHasTheBook(user, bookSlug);

        BookBorrowRequest bookBorrowRequest = BookBorrowRequest.builder()
                .user(user).bookSlug(bookSlug).requestDate(LocalDate.now()).build();

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
                .borrowDate(LocalDate.now())
                .returnDate((LocalDate.now().plusDays(30)))
                .build();

        addBookBorrowToUser(user, request, bookBorrow);

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

        if (!existsByUserIdAndBookSlug(userId, bookSlug).get()) {
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

    private void checkIfUserHasTheBook(User user, String bookSlug) throws BookBorrowConflictException {

        for (var request : user.getBookBorrows()) {
            if (request.getBookSlug().equals(bookSlug)) {
                throw new BookBorrowConflictException("User: " + user.getEmail()
                        + " already has the book already: " + bookSlug);
            }
        }
    }

}
