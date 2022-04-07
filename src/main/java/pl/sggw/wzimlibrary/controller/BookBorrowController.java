package pl.sggw.wzimlibrary.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.sggw.wzimlibrary.exception.UserHasTheBookAlreadyException;
import pl.sggw.wzimlibrary.exception.UserNotFoundException;
import pl.sggw.wzimlibrary.model.BookBorrowRequest;
import pl.sggw.wzimlibrary.model.annotation.CurrentlyLoggedUser;
import pl.sggw.wzimlibrary.service.BookBorrowService;

import java.net.URI;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/")
@CrossOrigin
@RequiredArgsConstructor
public class BookBorrowController {

    private final BookBorrowService bookBorrowService;

    @PostMapping("/bookBorrowRequests")
    public ResponseEntity<?> addBorrowBookRequest(@RequestBody String bookSlug,
                                                  @CurrentlyLoggedUser UserDetails userDetails)
            throws UserNotFoundException, ExecutionException, InterruptedException, UserHasTheBookAlreadyException {

        BookBorrowRequest bookBorrowRequest = bookBorrowService.addBookBorrowRequest(userDetails, bookSlug);

        return ResponseEntity.created(URI.create(
                        ServletUriComponentsBuilder.fromCurrentRequest().build() + "/user/"
                                + bookBorrowRequest.getUser().getId() + "/bookSlug/" + bookBorrowRequest.getBookSlug()))
                .build();
    }

}
