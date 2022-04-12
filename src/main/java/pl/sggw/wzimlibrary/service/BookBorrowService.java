package pl.sggw.wzimlibrary.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import pl.sggw.wzimlibrary.service.cache.UserCacheService;

import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class BookBorrowService {

    private final SqlBookBorrowRepository bookBorrowRepository;
    private final SqlBookBorrowRequestRepository bookBorrowRequestRepository;
    private final SqlBookBorrowProlongationRequestRepository bookBorrowProlongationRequestRepository;

    private final UserService userService;

    private final BookBorrowCacheService bookBorrowCacheService;

    private final UserCacheService userCacheService;

    @Async
    public CompletableFuture<BookBorrowRequest> save(BookBorrowRequest bookBorrowRequest) {
        return CompletableFuture.completedFuture(bookBorrowRequestRepository.save(bookBorrowRequest));
    }

    @Async
    public CompletableFuture<BookBorrow> save(BookBorrow bookBorrow) {
        return CompletableFuture.completedFuture(bookBorrowRepository.save(bookBorrow));
    }

    @Async
    public CompletableFuture<Boolean> existsByEmailAndBookSlug(String email, String bookSlug) {
        return CompletableFuture.completedFuture(bookBorrowRequestRepository.existsByUserEmailAndBookSlug(email, bookSlug));
    }

    @Async
    public CompletableFuture<BookBorrowRequest> getByEmailAndBookSlug(String email, String bookSlug) {
        return CompletableFuture.completedFuture(bookBorrowRequestRepository.getByUserEmailAndBookSlug(email, bookSlug));
    }


    @Transactional
    public BookBorrowRequest addBookBorrowRequest(UserDetails userDetails, String bookSlug)
            throws UserNotFoundException, ExecutionException, InterruptedException, BookBorrowConflictException {

        User user = userService.getUserFromUserDetails(userDetails);

        checkIfUserSentTheRequest(user, bookSlug);

        checkIfUserHasTheBook(user, bookSlug);

        BookBorrowRequest bookBorrowRequest = BookBorrowRequest.builder()
                .user(user).bookSlug(bookSlug).requestDate(LocalDate.now()).build();

        userCacheService.addBookBorrowRequestToUser(user, bookBorrowRequest);

        return bookBorrowRequest;

    }

    private void removeBookBorrowRequestFromUser(User user, BookBorrowRequest request) throws ExecutionException, InterruptedException {

        userCacheService.removeBookBorrowRequestFromUser(user, request);

    }

    @Transactional
    public void acceptBookBorrowRequest(BookBorrowDto bookBorrowDto)
            throws UserNotFoundException, BookBorrowConflictException, ExecutionException, InterruptedException {

        BookBorrowRequest request = getBookBorrowRequest(bookBorrowDto);

        deleteBookBorrowRequest(request);

        BookBorrow bb = BookBorrow.builder().user(request.getUser())
                .bookSlug("test")
                .borrowDate(LocalDate.now())
                .returnDate((LocalDate.now().plusDays(30)))
                .build();

        log.info(bb.getUser().getEmail() + " " + bb.getBookSlug());

        addBookBorrow(request.getUser(), request.getBookSlug());

    }

    @Transactional
    public void rejectBookBorrowRequest(BookBorrowDto bookBorrowDto)
            throws UserNotFoundException, BookBorrowConflictException, ExecutionException, InterruptedException {

        BookBorrowRequest request = getBookBorrowRequest(bookBorrowDto);

        deleteBookBorrowRequest(request);

    }

    BookBorrowRequest getBookBorrowRequest(BookBorrowDto bookBorrowDto)
            throws UserNotFoundException, ExecutionException, InterruptedException, BookBorrowConflictException {

        String email = bookBorrowDto.getEmail();
        String bookSlug = bookBorrowDto.getBookSlug();

        if (!userService.existsByEmail(email).get()) {
            throw new UserNotFoundException("User with email: " + bookBorrowDto.getEmail() + " not found");
        }

        // TODO: 08.04.2022 check if the book exists

        if (!existsByEmailAndBookSlug(email, bookSlug).get()) {
            throw new BookBorrowConflictException("There is no such request with the email: "
                    + email + " and the book: " + bookSlug);
        }

        return getByEmailAndBookSlug(email, bookSlug).get();
    }

    void deleteBookBorrowRequest(BookBorrowRequest bookBorrowRequest) throws ExecutionException, InterruptedException {

        User user = bookBorrowRequest.getUser();

        removeBookBorrowRequestFromUser(user, bookBorrowRequest);

    }

    BookBorrow addBookBorrow(User user, String bookSlug) throws ExecutionException, InterruptedException {

        log.info(user.getId().toString());

        //BookBorrow bb = new BookBorrow(user, bookSlug, LocalDate.now(), LocalDate.now().plusDays(30));

        BookBorrow bb = new BookBorrow();

        log.info(bb.getUser().getEmail() + " " + bb.getBookSlug());

        user.getBookBorrows().add(bb);
        userService.save(user);
        return save(bb).get();


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
