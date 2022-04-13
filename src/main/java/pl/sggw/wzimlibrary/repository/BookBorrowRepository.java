package pl.sggw.wzimlibrary.repository;

import pl.sggw.wzimlibrary.model.BookBorrow;

import java.sql.Date;

public interface BookBorrowRepository {

    <S extends BookBorrow> S save(S s);

    int deleteAllFromUserByReturnDate(Integer userId, Date currentDate);
}
