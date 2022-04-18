package pl.sggw.wzimlibrary.repository;

import pl.sggw.wzimlibrary.model.BookBorrow;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface BookBorrowRepository {

    <S extends BookBorrow> S save(S s);

    int deleteAllFromUserByReturnDate(Integer userId, Date currentDate);

    Optional<BookBorrow> findByUser_IdAndBookSlug(Integer userId, String bookSlug);

    List<BookBorrow> findAll();

    List<BookBorrow> findAllByUser_Id(Integer userId);
}
