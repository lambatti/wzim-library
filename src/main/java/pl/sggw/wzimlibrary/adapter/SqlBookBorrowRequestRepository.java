package pl.sggw.wzimlibrary.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sggw.wzimlibrary.model.BookBorrowRequest;
import pl.sggw.wzimlibrary.model.id.BookBorrowId;
import pl.sggw.wzimlibrary.repository.BookBorrowRequestRepository;

public interface SqlBookBorrowRequestRepository extends BookBorrowRequestRepository,
        JpaRepository<BookBorrowRequest, BookBorrowId> {

    @Override
    <S extends BookBorrowRequest> S save(S s);

}
