package pl.sggw.wzimlibrary.api.wolnelektury.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.sggw.wzimlibrary.api.wolnelektury.model.BookSlug;
import pl.sggw.wzimlibrary.api.wolnelektury.model.BookWolnelektury;
import pl.sggw.wzimlibrary.api.wolnelektury.model.NameProp;

import javax.naming.Name;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
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

    public Set<NameProp> getAllEpochs() {
        final List<BookWolnelektury> bookList = getAllBook();
        final Set<NameProp> allEpochs = new HashSet<>();
        bookList.forEach(book -> allEpochs.addAll(book.getEpochs()));
        return allEpochs;
    }

    public Set<NameProp> getAllKinds() {
        final List<BookWolnelektury> bookList = getAllBook();
        final Set<NameProp> allKinds = new HashSet<>();
        bookList.forEach(book -> allKinds.addAll(book.getKinds()));
        return allKinds;
    }

    public Set<NameProp> getAllGenres() {
        final List<BookWolnelektury> bookList = getAllBook();
        final Set<NameProp> allGenres = new HashSet<>();
        bookList.forEach(book -> allGenres.addAll(book.getGenres()));
        return allGenres;
    }
}
