package pl.sggw.wzimlibrary.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.sggw.wzimlibrary.model.BookBorrowRequest;
import pl.sggw.wzimlibrary.repository.BookBorrowRequestRepository;

public interface SqlBookBorrowRequestRepository extends BookBorrowRequestRepository,
        JpaRepository<BookBorrowRequest, Integer> {

    @Override
    <S extends BookBorrowRequest> S save(S s);

    @Override
    @Query(nativeQuery = true, value = "SELECT CASE WHEN COUNT(1) THEN 'true' ELSE 'false' END FROM book_borrows_requests" +
            " WHERE user_id = :userId AND book_slug = :bookSlug ")
    boolean existsByUserIdAndBookSlug(@Param("userId") Integer userId, @Param("bookSlug") String bookSlug);

    @Override
    @Query(nativeQuery = true, value = "SELECT * FROM book_borrows_requests" +
            " WHERE user_id = :userId AND book_slug = :bookSlug ")
    BookBorrowRequest getByUserIdAndBookSlug(@Param("userId") Integer userId, @Param("bookSlug") String bookSlug);

    @Override
    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM book_borrows_requests" +
            " WHERE user_id = :userId AND book_slug = :bookSlug  ")
    void deleteByUserAndBookSlug(@Param("userId") Integer userId, @Param("bookSlug") String bookSlug);

}
