package pl.sggw.wzimlibrary.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sggw.wzimlibrary.model.BookBorrowProlongationRequest;
import pl.sggw.wzimlibrary.model.id.BookBorrowId;
import pl.sggw.wzimlibrary.repository.BookBorrowProlongationRequestRepository;

public interface SqlBookBorrowProlongationRequestRepository extends
        BookBorrowProlongationRequestRepository, JpaRepository<BookBorrowProlongationRequest, BookBorrowId> {

    @Override
    <S extends BookBorrowProlongationRequest> S save(S s);

}
