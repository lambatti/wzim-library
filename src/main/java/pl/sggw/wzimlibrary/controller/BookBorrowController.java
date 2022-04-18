package pl.sggw.wzimlibrary.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.sggw.wzimlibrary.exception.BookBorrowConflictException;
import pl.sggw.wzimlibrary.exception.BookBorrowNotFoundException;
import pl.sggw.wzimlibrary.exception.UserNotFoundException;
import pl.sggw.wzimlibrary.model.BookBorrowProlongationRequest;
import pl.sggw.wzimlibrary.model.BookBorrowRequest;
import pl.sggw.wzimlibrary.model.annotation.CurrentlyLoggedUser;
import pl.sggw.wzimlibrary.model.dto.bookborrow.BookBorrowDto;
import pl.sggw.wzimlibrary.service.BookBorrowService;

import java.net.URI;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/")
@CrossOrigin
@RequiredArgsConstructor
public class BookBorrowController {

    private final BookBorrowService bookBorrowService;

    @GetMapping("bookBorrows")
    public ResponseEntity<?> getBookBorrows() throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(bookBorrowService.findAllBookBorrows().get());
    }

    @GetMapping("bookBorrows/user/{userId}")
    public ResponseEntity<?> getBookBorrows(@PathVariable Integer userId) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(bookBorrowService.findAllBookBorrowsByUserId(userId).get());
    }

    @GetMapping("bookBorrows/user/{userId}/bookSlug/{bookSlug}")
    public ResponseEntity<?> getBookBorrow(@PathVariable Integer userId, @PathVariable String bookSlug)
            throws ExecutionException, InterruptedException, BookBorrowNotFoundException {
        return ResponseEntity.ok(bookBorrowService.getBookBorrowByUserIdAndBookSlug(userId, bookSlug));

    }

    @GetMapping("bookBorrowRequests")
    public ResponseEntity<?> getBookBorrowRequests() throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(bookBorrowService.findAllRequests().get());
    }

    @GetMapping("bookBorrowRequests/user/{userId}")
    public ResponseEntity<?> getBookBorrowRequests(@PathVariable Integer userId) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(bookBorrowService.findAllRequestsByUserId(userId).get());
    }

    @GetMapping("bookBorrowRequests/user/{userId}/bookSlug/{bookSlug}")
    public ResponseEntity<?> getBookBorrowRequest(@PathVariable Integer userId, @PathVariable String bookSlug)
            throws ExecutionException, InterruptedException, BookBorrowNotFoundException {
        return ResponseEntity.ok(bookBorrowService.getRequestByUserIdAndBookSlug(userId, bookSlug));

    }


    @PostMapping("bookBorrowRequests")
    public ResponseEntity<?> addBorrowBookRequest(@RequestBody String bookSlug,
                                                  @CurrentlyLoggedUser UserDetails userDetails)
            throws UserNotFoundException, ExecutionException, InterruptedException, BookBorrowConflictException {

        BookBorrowRequest bookBorrowRequest = bookBorrowService.addBookBorrowRequest(userDetails, bookSlug);

        return ResponseEntity.created(URI.create(
                        ServletUriComponentsBuilder.fromCurrentRequest().build() + "/user/"
                                + bookBorrowRequest.getUser().getId() + "/bookSlug/" + bookBorrowRequest.getBookSlug()))
                .build();
    }

    @PostMapping("bookBorrowRequests/accept")
    public ResponseEntity<?> acceptBookBorrowRequest(@RequestBody BookBorrowDto bookBorrowDto)
            throws UserNotFoundException, BookBorrowConflictException, ExecutionException, InterruptedException {

        bookBorrowService.acceptBookBorrowRequest(bookBorrowDto);

        return ResponseEntity.ok().build();
    }

    @PostMapping("bookBorrowRequests/reject")
    public ResponseEntity<?> rejectBookBorrowRequest(@RequestBody BookBorrowDto bookBorrowDto)
            throws UserNotFoundException, BookBorrowConflictException, ExecutionException, InterruptedException {

        bookBorrowService.rejectBookBorrowRequest(bookBorrowDto);

        return ResponseEntity.ok().build();
    }

    @GetMapping("bookBorrowProlongationRequests")
    public ResponseEntity<?> getBookBorrowProlongationRequests() throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(bookBorrowService.findAllProlongationRequests().get());
    }

    @GetMapping("bookBorrowProlongationRequests/user/{userId}")
    public ResponseEntity<?> getBookBorrowProlongationRequests(@PathVariable Integer userId) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(bookBorrowService.findAllProlongationRequestsByUserId(userId).get());
    }

    @GetMapping("bookBorrowProlongationRequests/user/{userId}/bookSlug/{bookSlug}")
    public ResponseEntity<?> getBookBorrowProlongationRequest(@PathVariable Integer userId, @PathVariable String bookSlug)
            throws ExecutionException, InterruptedException, BookBorrowNotFoundException {
        return ResponseEntity.ok(bookBorrowService.getProlongationRequestByUserIdAndBookSlug(userId, bookSlug));

    }

    @PostMapping("bookBorrowProlongationRequests")
    public ResponseEntity<?> addBookBorrowProlongationRequest(@RequestBody String bookSlug,
                                                              @CurrentlyLoggedUser UserDetails userDetails)
            throws UserNotFoundException, BookBorrowConflictException, ExecutionException, InterruptedException {
        BookBorrowProlongationRequest request = bookBorrowService.addBookBorrowProlongationRequest(userDetails, bookSlug);

        return ResponseEntity.created(URI.create(
                        ServletUriComponentsBuilder.fromCurrentRequest().build() + "/user/"
                                + request.getUser().getId() + "/bookSlug/" + request.getBookSlug()))
                .build();
    }

    @PostMapping("bookBorrowProlongationRequests/accept")
    public ResponseEntity<?> acceptBookBorrowProlongationRequest(@RequestBody BookBorrowDto bookBorrowDto)
            throws UserNotFoundException, BookBorrowConflictException, ExecutionException, InterruptedException {

        bookBorrowService.acceptBookBorrowProlongationRequest(bookBorrowDto);

        return ResponseEntity.ok().build();
    }

    @PostMapping("bookBorrowProlongationRequests/reject")
    public ResponseEntity<?> rejectBookBorrowProlongationRequest(@RequestBody BookBorrowDto bookBorrowDto)
            throws UserNotFoundException, BookBorrowConflictException, ExecutionException, InterruptedException {

        bookBorrowService.rejectBookBorrowProlongationRequest(bookBorrowDto);

        return ResponseEntity.ok().build();
    }
}

