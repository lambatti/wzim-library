package pl.sggw.wzimlibrary.api.wolnelektury.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.sggw.wzimlibrary.api.wolnelektury.model.BookWolnelekturyDetailedSlug;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookWolnelekturyDetailedSlugService {
    private final RestTemplate restTemplate;

    public BookWolnelekturyDetailedSlug getBookBySlug(final String bookSlug) {
        log.debug("Pobieram detailedSlug: {}", bookSlug);
        final String urlTemplate = UriComponentsBuilder.fromPath("/books/" + bookSlug + "/")
                .queryParam("format", "json")
                .encode()
                .toUriString();
        try {
            BookWolnelekturyDetailedSlug output = restTemplate.getForObject(urlTemplate, BookWolnelekturyDetailedSlug.class);
            output.setSlug(bookSlug);
            return output;
        } catch (final HttpClientErrorException.NotFound e) {
            return null; // nie znaleziono
        }
    }
}
