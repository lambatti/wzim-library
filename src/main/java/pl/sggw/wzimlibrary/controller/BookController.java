package pl.sggw.wzimlibrary.controller;


import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sggw.wzimlibrary.model.book.Book;
import pl.sggw.wzimlibrary.model.book.BookURL;
import pl.sggw.wzimlibrary.service.BookService;
import pl.sggw.wzimlibrary.service.BookURLService;

import java.util.List;

@RestController
@RequestMapping(path = "/books")
public class BookController {
    private final BookURLService bookURLService;
    private final BookService bookService;

    public BookController(BookURLService bookURLService,
                          BookService bookService) {
        this.bookURLService = bookURLService;
        this.bookService = bookService;
    }

    @GetMapping
    ResponseEntity<List<BookURL>> readAllBooks() {
        return ResponseEntity.ok(bookURLService.getBookURLList());
    }

    @GetMapping(path = "/details")
    ResponseEntity<List<Book>> readAllBooksDetails() {
        return ResponseEntity.ok(bookService.getBookList());
    }

    @GetMapping(path = "/details/test")
    ResponseEntity<Book> readAllBooksDetailsTest() {
        return ResponseEntity.ok(bookService.getBookByUrl("20-000-mil-podmorskiej-zeglugi"));
    }
}
