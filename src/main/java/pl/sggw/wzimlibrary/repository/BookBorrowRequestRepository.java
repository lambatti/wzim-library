package pl.sggw.wzimlibrary.repository;

import pl.sggw.wzimlibrary.model.BookBorrowRequest;

public interface BookBorrowRequestRepository {

    <S extends BookBorrowRequest> S save(S s);

    boolean existsByUserIdAndBookSlug(Integer userId, String bookSlug);

    BookBorrowRequest getByUserIdAndBookSlug(Integer userId, String bookSlug);

    void deleteByUserAndBookSlug(Integer userId, String bookSlug);

}
