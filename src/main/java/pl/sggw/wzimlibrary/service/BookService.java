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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
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

    public List<Book> getAllBooksByKind(String kind) {
        final List<Book> allBooks = this.getAllBooks();
        return allBooks.stream()
                .filter(book -> book.getKinds().contains(kind)).collect(Collectors.toList());
    }

    public List<NameProp> getBookKinds() {
        return bookNamePropService.getNameProp("kinds");
    }

    public List<NameProp> getBookEpochs() {
        return bookNamePropService.getNameProp("epochs");
    }

    public List<NameProp> getBookGenres() {
        return bookNamePropService.getNameProp("genres");
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
