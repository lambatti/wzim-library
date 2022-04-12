package pl.sggw.wzimlibrary.repository;

import pl.sggw.wzimlibrary.model.BookBorrowRequest;

public interface BookBorrowRequestRepository {

    <S extends BookBorrowRequest> S save(S s);

    boolean existsByUserEmailAndBookSlug(String email, String bookSlug);

    BookBorrowRequest getByUserEmailAndBookSlug(String email, String bookSlug);

    void deleteByUserAndBookSlug(Integer userId, String bookSlug);

}
