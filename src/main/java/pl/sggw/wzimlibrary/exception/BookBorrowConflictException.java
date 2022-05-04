package pl.sggw.wzimlibrary.exception;

public class BookBorrowConflictException extends Exception {
    public BookBorrowConflictException(String message) {
        super(message);
    }
}
