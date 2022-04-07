package pl.sggw.wzimlibrary.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pl.sggw.wzimlibrary.adapter.SqlBookBorrowProlongationRequestRepository;
import pl.sggw.wzimlibrary.adapter.SqlBookBorrowRepository;
import pl.sggw.wzimlibrary.adapter.SqlBookBorrowRequestRepository;
import pl.sggw.wzimlibrary.exception.UserNotFoundException;
import pl.sggw.wzimlibrary.model.BookBorrowRequest;
import pl.sggw.wzimlibrary.model.User;
import pl.sggw.wzimlibrary.model.id.BookBorrowId;

import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


@Service
@RequiredArgsConstructor
public class BookBorrowService {

    private final SqlBookBorrowRepository bookBorrowRepository;
    private final SqlBookBorrowRequestRepository bookBorrowRequestRepository;
    private final SqlBookBorrowProlongationRequestRepository bookBorrowProlongationRequestRepository;

    private final UserService userService;

    @Async
    public CompletableFuture<BookBorrowRequest> save(BookBorrowRequest bookBorrowRequest) {
        return CompletableFuture.completedFuture(bookBorrowRequestRepository.save(bookBorrowRequest));
    }

    public BookBorrowRequest addBookBorrowRequest(UserDetails userDetails, String slug)
            throws UserNotFoundException, ExecutionException, InterruptedException {
        User user = userService.getUserFromUserDetails(userDetails);
        BookBorrowId bookBorrowId = new BookBorrowId(user, slug);
        BookBorrowRequest bookBorrowRequest = new BookBorrowRequest(bookBorrowId, LocalDate.now());
        return save(bookBorrowRequest).get();
    }

}
