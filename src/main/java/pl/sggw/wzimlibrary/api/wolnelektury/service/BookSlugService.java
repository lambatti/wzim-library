package pl.sggw.wzimlibrary.api.wolnelektury.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.sggw.wzimlibrary.api.wolnelektury.model.BookSlug;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookSlugService {
    private final RestTemplate restTemplate;

    public List<BookSlug> getBookURLList() {
        return restTemplate
                .exchange("/books/",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<BookSlug>>(){})
                .getBody();
    }
}