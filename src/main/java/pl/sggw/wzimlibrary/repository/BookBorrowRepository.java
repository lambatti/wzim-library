package pl.sggw.wzimlibrary.repository;

import pl.sggw.wzimlibrary.model.BookBorrow;

public interface BookBorrowRepository {

    <S extends BookBorrow> S save(S s);

}
