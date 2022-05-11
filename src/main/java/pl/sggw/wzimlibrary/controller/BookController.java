package pl.sggw.wzimlibrary.controller;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sggw.wzimlibrary.api.wolnelektury.model.NameProp;
import pl.sggw.wzimlibrary.model.Book;
import pl.sggw.wzimlibrary.model.dto.BookReadDto;
import pl.sggw.wzimlibrary.service.BookService;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping
    ResponseEntity<List<Book>> readAllBooksDetails() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping(path = "/{slug}")
    ResponseEntity<Book> readBookBySlug(@PathVariable String slug) {
        return ResponseEntity.ok(bookService.getBookBySlug(slug));
    }

    @GetMapping(path = "/kinds/{kind}")
    ResponseEntity<List<Book>> readAllBooksByKind(@PathVariable String kind) {
        return ResponseEntity.ok(bookService.getAllBooksByKind(kind));
    }

    @GetMapping(path = "/kinds")
    ResponseEntity<List<NameProp>> readBookKinds() {
        return ResponseEntity.ok(bookService.getBookKinds());
    }

    @GetMapping(path = "/epochs")
    ResponseEntity<List<NameProp>> readBookEpochs() {
        return ResponseEntity.ok(bookService.getBookEpochs());
    }

    @GetMapping(path = "/genres")
    ResponseEntity<List<NameProp>> readBookGenres() {
        return ResponseEntity.ok(bookService.getBookGenres());
    }

    @GetMapping(path = "/read/{slug}")
    ResponseEntity<BookReadDto> readBookRead(@PathVariable String slug) {
        return ResponseEntity.ok(bookService.getBookRead(slug));
    }

    @GetMapping(path = "/home")
    ResponseEntity<List<Book>> readHomePageBooks() {
        return ResponseEntity.ok(bookService.getHomePageBooks(Arrays.asList("Liryka", "Epika", "Dramat"),
                4)
        );
    }

    @GetMapping(path = "/best")
    ResponseEntity<List<Book>> readBestPage() {
        return ResponseEntity.ok(bookService.getRandomBooks(24));
    }
}
