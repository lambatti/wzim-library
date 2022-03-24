package pl.sggw.wzimlibrary.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sggw.wzimlibrary.model.Book;
import pl.sggw.wzimlibrary.service.BookService;

import java.util.List;

@RestController
@RequestMapping(path = "/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping
    ResponseEntity<List<Book>> readAllBooksDetails() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

}
