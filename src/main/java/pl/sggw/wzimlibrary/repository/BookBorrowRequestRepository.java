package pl.sggw.wzimlibrary.repository;

import pl.sggw.wzimlibrary.model.BookBorrowRequest;

public interface BookBorrowRequestRepository {

    <S extends BookBorrowRequest> S save(S s);

}
