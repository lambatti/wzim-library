package pl.sggw.wzimlibrary.api.wolnelektury.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.sggw.wzimlibrary.api.wolnelektury.model.BookSlug;
import pl.sggw.wzimlibrary.api.wolnelektury.model.BookWolnelektury;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AllBooksWolnelekturyService {
    private final BookSlugService bookSlugService;
    private final BookWolnelekturyService bookServiceWolnelektury;
    @Value("${app.books.max}")
    private Integer maxSize;

    public List<BookWolnelektury> getAllBook() {
        // TODO: URL -> Slug
        final List<BookSlug> bookSlugList = bookSlugService.getBookURLList();
        return bookSlugList.parallelStream()
                .map(BookSlug::getSlug)
                .limit(maxSize)
                .map(this.bookServiceWolnelektury::getBookBySlug)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
