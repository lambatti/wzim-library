package pl.sggw.wzimlibrary.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.sggw.wzimlibrary.model.BookBorrow;
import pl.sggw.wzimlibrary.repository.BookBorrowRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface SqlBookBorrowRepository extends BookBorrowRepository, JpaRepository<BookBorrow, Integer> {

    @Override
    <S extends BookBorrow> S save(S s);

    @Override
    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM book_borrows WHERE user_id = :userId AND return_date < :currentDate")
    int deleteAllFromUserByReturnDate(@Param("userId") Integer userId, @Param("currentDate") Date currentDate);

    @Override
    Optional<BookBorrow> findByUser_IdAndBookSlug(Integer userId, String bookSlug);

    @Override
    List<BookBorrow> findAll();

    @Override
    List<BookBorrow> findAllByUser_Id(Integer userId);
}
