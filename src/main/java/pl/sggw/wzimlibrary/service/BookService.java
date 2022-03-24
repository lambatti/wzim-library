package pl.sggw.wzimlibrary.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import pl.sggw.wzimlibrary.api.wolnelektury.model.BookWolnelektury;
import pl.sggw.wzimlibrary.api.wolnelektury.service.AllBooksWolnelekturyService;
import pl.sggw.wzimlibrary.model.Book;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final ModelMapper modelMapper;
    private final AllBooksWolnelekturyService allBooksWolnelekturyService;

    public List<Book> getAllBooks() {
        final List<BookWolnelektury> allBook = allBooksWolnelekturyService.getAllBook();
        return modelMapper
                .map(allBook, new TypeToken<List<Book>>() {
                }.getType());
    }
}
