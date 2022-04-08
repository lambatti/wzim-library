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

    @Async
    public CompletableFuture<BookBorrowRequest> save(BookBorrowRequest bookBorrowRequest) {
        return CompletableFuture.completedFuture(bookBorrowRequestRepository.save(bookBorrowRequest));
    }

    @Transactional
    public BookBorrowRequest addBookBorrowRequest(UserDetails userDetails, String bookSlug)
            throws UserNotFoundException, ExecutionException, InterruptedException, BookBorrowConflictException {

        User user = userService.getUserFromUserDetails(userDetails);

        checkIfUserSentTheRequest(user, bookSlug);

        checkIfUserHasTheBook(user, bookSlug);

        BookBorrowRequest bookBorrowRequest = new BookBorrowRequest(user, bookSlug, LocalDate.now());

        user.getBookBorrowRequests().add(bookBorrowRequest);

        userService.save(user);

        return save(bookBorrowRequest).get();

    }

    public BookBorrow addBookBorrow(User user, String slug) throws Exception {
        throw new Exception("Not implemented yet");
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
