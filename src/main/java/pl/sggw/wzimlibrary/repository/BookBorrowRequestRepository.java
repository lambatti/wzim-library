package pl.sggw.wzimlibrary.repository;

import pl.sggw.wzimlibrary.model.BookBorrowRequest;

public interface BookBorrowRequestRepository {

    <S extends BookBorrowRequest> S save(S s);

    boolean existsByUser_IdAndBookSlug(Integer userId, String bookSlug);

    BookBorrowRequest getByUser_IdAndBookSlug(Integer userId, String bookSlug);

}
