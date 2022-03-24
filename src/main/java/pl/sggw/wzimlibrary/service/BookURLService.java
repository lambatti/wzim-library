package pl.sggw.wzimlibrary.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pl.sggw.wzimlibrary.model.book.BookURL;

import java.util.List;

@Service
public class BookURLService {

    private final WebClient webClient;

    public BookURLService(WebClient.Builder builder) {
        webClient = builder.baseUrl("https://wolnelektury.pl/")
                .build();
    }

    public List<BookURL> getBookURLList() {
        return webClient
                .get()
                .uri("/api/books/")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<BookURL>>() {
                })
                .block();
    }

}
