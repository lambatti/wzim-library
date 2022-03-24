package pl.sggw.wzimlibrary.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pl.sggw.wzimlibrary.model.book.Book;
import pl.sggw.wzimlibrary.model.book.BookURL;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    private final WebClient webClient;
    private final BookURLService bookURLService;

    public BookService(WebClient.Builder builder, BookURLService bookURLService) {
        webClient = builder.baseUrl("https://wolnelektury.pl/")
                .build();
        this.bookURLService = bookURLService;
    }

    public List<Book> getBookList() {
        List<BookURL> bookURLS = bookURLService.getBookURLList();
        List<Book> bookList = new ArrayList<>();
        for (BookURL url : bookURLS) {
            bookList.add(getBookByUrl(url.getSlug()));
        }
        return bookList;
    }

    public Book getBookByUrl(String url) {
        return webClient
                .get()
                .uri("/api/books/" + url + "/?format=json")
                .retrieve()
                .bodyToMono(Book.class)
                .block();
    }
}
