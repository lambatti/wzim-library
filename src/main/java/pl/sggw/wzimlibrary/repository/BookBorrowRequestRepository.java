package pl.sggw.wzimlibrary.repository;

import pl.sggw.wzimlibrary.model.BookBorrowRequest;

import java.util.Optional;

public interface BookBorrowRequestRepository {

    <S extends BookBorrowRequest> S save(S s);

    boolean existsByUser_IdAndBookSlug(Integer userId, String bookSlug);

    Optional<BookBorrowRequest> findByUser_IdAndBookSlug(Integer userId, String bookSlug);

}
