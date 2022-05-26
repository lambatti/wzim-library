package pl.sggw.wzimlibrary.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import pl.sggw.wzimlibrary.api.wolnelektury.model.BookWolnelektury;
import pl.sggw.wzimlibrary.api.wolnelektury.model.NameProp;
import pl.sggw.wzimlibrary.api.wolnelektury.service.AllBooksWolnelekturyService;
import pl.sggw.wzimlibrary.api.wolnelektury.service.BookNamePropService;
import pl.sggw.wzimlibrary.api.wolnelektury.service.BookWolnelekturyService;
import pl.sggw.wzimlibrary.model.Book;
import pl.sggw.wzimlibrary.model.dto.BookReadDto;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final ModelMapper modelMapper;
    private final AllBooksWolnelekturyService allBooksWolnelekturyService;
    private final BookWolnelekturyService bookWolnelekturyService;
    private final BookNamePropService bookNamePropService;
    private boolean isBestShuffle = false;
    private List<Book> randomBookList = new ArrayList<>();


    public List<Book> getAllBooks() {
        final List<BookWolnelektury> allBook = allBooksWolnelekturyService.getAllBook();
        return modelMapper
                .map(allBook, new TypeToken<List<Book>>() {
                }.getType());
    }

    public String getTitleBySlug(String slug) {
        return bookWolnelekturyService.getBookBySlug(slug).getTitle();
    }

    public Book getBookBySlug(String slug) {
        final BookWolnelektury bookBySlug = bookWolnelekturyService.getBookBySlug(slug);
        return modelMapper
                .map(bookBySlug, new TypeToken<Book>() {
                }.getType());
    }

    public Set<NameProp> getBookKinds() {
        return allBooksWolnelekturyService.getAllKinds();
    }

    public List<Book> getAllBooksByKind(String kind) {
        final List<Book> allBooks = this.getAllBooks();
        return allBooks.stream()
                .filter(book -> book.getKinds().contains(kind)).collect(Collectors.toList());
    }

    public Set<NameProp> getBookEpochs() {
        return allBooksWolnelekturyService.getAllEpochs();
    }

    public List<Book> getAllBooksByEpoch(String epoch) {
        final List<Book> allBooks = this.getAllBooks();
        return allBooks.stream()
                .filter(book -> book.getEpochs().contains(epoch)).collect(Collectors.toList());
    }

    public Set<NameProp> getBookGenres() {
        return allBooksWolnelekturyService.getAllGenres();
    }

    public List<Book> getAllBooksByGenre(String genre) {
        final List<Book> allBooks = this.getAllBooks();
        return allBooks.stream()
                .filter(book -> book.getGenres().contains(genre)).collect(Collectors.toList());
    }

    public BookReadDto getBookRead(String slug) {
        return modelMapper.map(getBookBySlug(slug), BookReadDto.class);
    }

    public List<Book> getHomePageBooks(List<String> kinds, Integer numberOfOneKindBooks) {
        List<Book> homePageBooks = new ArrayList<>();
        for (String kind : kinds) {
            homePageBooks.addAll(getAllBooksByKind(kind).subList(0, numberOfOneKindBooks));
        }
        return homePageBooks;
    }

    public List<Book> getRandomBooks(Integer numberOfBooks) {
        if (!isBestShuffle) {
            randomBookList = getAllBooks();
            Collections.shuffle(randomBookList);
            isBestShuffle = true;
        }
        return randomBookList.subList(0, numberOfBooks);
    }

}
