package pl.sggw.wzimlibrary.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sggw.wzimlibrary.model.BookBorrowRequest;
import pl.sggw.wzimlibrary.repository.BookBorrowRequestRepository;

public interface SqlBookBorrowRequestRepository extends BookBorrowRequestRepository,
        JpaRepository<BookBorrowRequest, Integer> {

    @Override
    <S extends BookBorrowRequest> S save(S s);

    @Override
    boolean existsByUser_IdAndBookSlug(Integer userId, String bookSlug);

    @Override
    BookBorrowRequest getByUser_IdAndBookSlug(Integer userId, String bookSlug);

}
