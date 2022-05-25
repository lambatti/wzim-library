package pl.sggw.wzimlibrary.api.wolnelektury.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.sggw.wzimlibrary.api.wolnelektury.model.BookWolnelektury;
import pl.sggw.wzimlibrary.api.wolnelektury.model.NameProp;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookWolnelekturyService {
    private final RestTemplate restTemplate;

    @Cacheable(value = "bookBySlug", key = "#bookSlug")
    public BookWolnelektury getBookBySlug(final String bookSlug) {
        log.debug("Pobieram: {}", bookSlug);
        final String urlTemplate = UriComponentsBuilder.fromPath("/books/" + bookSlug + "/")
                .queryParam("format", "json")
                .encode()
                .toUriString();
        try {
            BookWolnelektury output = restTemplate.getForObject(urlTemplate, BookWolnelektury.class);
            output.setSlug(bookSlug);
            return output;
        } catch (final HttpClientErrorException.NotFound e) {
            return null; // nie znaleziono
        }
    }

}
