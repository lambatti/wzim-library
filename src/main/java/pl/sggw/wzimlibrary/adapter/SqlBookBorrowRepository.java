package pl.sggw.wzimlibrary.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sggw.wzimlibrary.model.BookBorrow;
import pl.sggw.wzimlibrary.model.id.BookBorrowId;
import pl.sggw.wzimlibrary.repository.BookBorrowRepository;

public interface SqlBookBorrowRepository extends BookBorrowRepository, JpaRepository<BookBorrow, BookBorrowId> {

    @Override
    <S extends BookBorrow> S save(S s);

}
