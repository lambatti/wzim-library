package pl.sggw.wzimlibrary.model.constant;

import java.time.LocalDate;

public final class BookBorrowConstant {
    public final static int BOOK_BORROW_DAYS = 30;
    public final static LocalDate CURRENT_DATE = LocalDate.now();
    public final static LocalDate RETURN_DATE = CURRENT_DATE.plusDays(BOOK_BORROW_DAYS);

    public static LocalDate addBookBorrowDays(LocalDate date) {
        return date.plusDays(BOOK_BORROW_DAYS);
    }

}
