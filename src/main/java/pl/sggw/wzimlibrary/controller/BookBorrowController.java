package pl.sggw.wzimlibrary.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.sggw.wzimlibrary.exception.BookBorrowConflictException;
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

    @PatchMapping("bookBorrowRequests/accept")
    public ResponseEntity<?> acceptBookBorrowRequest(@RequestBody BookBorrowDto bookBorrowDto)
            throws UserNotFoundException, BookBorrowConflictException, ExecutionException, InterruptedException {

        bookBorrowService.acceptBookBorrowRequest(bookBorrowDto);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("bookBorrowRequests/reject")
    public ResponseEntity<?> rejectBookBorrowRequest(@RequestBody BookBorrowDto bookBorrowDto)
            throws UserNotFoundException, BookBorrowConflictException, ExecutionException, InterruptedException {

        bookBorrowService.rejectBookBorrowRequest(bookBorrowDto);

        return ResponseEntity.ok().build();
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

    @PatchMapping("bookBorrowProlongationRequests/accept")
    public ResponseEntity<?> acceptBookBorrowProlongationRequest(@RequestBody BookBorrowDto bookBorrowDto)
            throws UserNotFoundException, BookBorrowConflictException, ExecutionException, InterruptedException {

        bookBorrowService.acceptBookBorrowProlongationRequest(bookBorrowDto);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("bookBorrowProlongationRequests/reject")
    public ResponseEntity<?> rejectBookBorrowProlongationRequest(@RequestBody BookBorrowDto bookBorrowDto)
            throws UserNotFoundException, BookBorrowConflictException, ExecutionException, InterruptedException {

        bookBorrowService.rejectBookBorrowProlongationRequest(bookBorrowDto);

        return ResponseEntity.ok().build();
    }
}

