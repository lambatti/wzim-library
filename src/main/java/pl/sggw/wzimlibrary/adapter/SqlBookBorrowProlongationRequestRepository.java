package pl.sggw.wzimlibrary.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sggw.wzimlibrary.model.BookBorrowProlongationRequest;
import pl.sggw.wzimlibrary.repository.BookBorrowProlongationRequestRepository;

import java.util.List;
import java.util.Optional;

public interface SqlBookBorrowProlongationRequestRepository extends
        BookBorrowProlongationRequestRepository, JpaRepository<BookBorrowProlongationRequest, Integer> {

    @Override
    <S extends BookBorrowProlongationRequest> S save(S s);

    @Override
    boolean existsByUser_IdAndBookSlug(Integer userId, String bookSlug);

    @Override
    Optional<BookBorrowProlongationRequest> findByUser_IdAndBookSlug(Integer userId, String bookSlug);

    @Override
    List<BookBorrowProlongationRequest> findAll();

    @Override
    List<BookBorrowProlongationRequest> findAllByUser_Id(Integer userId);
}
