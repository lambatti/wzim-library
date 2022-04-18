package pl.sggw.wzimlibrary.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sggw.wzimlibrary.model.BookBorrowRequest;
import pl.sggw.wzimlibrary.repository.BookBorrowRequestRepository;

import java.util.List;
import java.util.Optional;

public interface SqlBookBorrowRequestRepository extends BookBorrowRequestRepository,
        JpaRepository<BookBorrowRequest, Integer> {

    @Override
    <S extends BookBorrowRequest> S save(S s);

    @Override
    boolean existsByUser_IdAndBookSlug(Integer userId, String bookSlug);

    @Override
    Optional<BookBorrowRequest> findByUser_IdAndBookSlug(Integer userId, String bookSlug);

    @Override
    List<BookBorrowRequest> findAll();

    @Override
    List<BookBorrowRequest> findAllByUser_Id(Integer userId);

}
