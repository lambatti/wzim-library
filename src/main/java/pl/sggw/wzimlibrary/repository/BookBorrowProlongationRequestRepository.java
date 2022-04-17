package pl.sggw.wzimlibrary.repository;

import pl.sggw.wzimlibrary.model.BookBorrowProlongationRequest;

public interface BookBorrowProlongationRequestRepository {

    <S extends BookBorrowProlongationRequest> S save(S s);

    boolean existsByUser_IdAndBookSlug(Integer userId, String bookSlug);
    
}
