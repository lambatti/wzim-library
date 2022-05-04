package pl.sggw.wzimlibrary.repository;

import pl.sggw.wzimlibrary.model.BookBorrowProlongationRequest;

import java.util.List;
import java.util.Optional;

public interface BookBorrowProlongationRequestRepository {

    <S extends BookBorrowProlongationRequest> S save(S s);

    boolean existsByUser_IdAndBookSlug(Integer userId, String bookSlug);

    Optional<BookBorrowProlongationRequest> findByUser_IdAndBookSlug(Integer userId, String bookSlug);

    List<BookBorrowProlongationRequest> findAll();

    List<BookBorrowProlongationRequest> findAllByUser_Id(Integer userId);

}
