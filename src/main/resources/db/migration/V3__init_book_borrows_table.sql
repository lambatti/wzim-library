DROP TABLE IF EXISTS book_borrows;

CREATE TABLE book_borrows
(
    user_id     INT          NOT NULL,
    book_slug   VARCHAR(255) NOT NULL,
    borrow_date DATE,
    return_date DATE,
    CONSTRAINT book_borrow_cpk PRIMARY KEY (user_id, book_slug),
    CONSTRAINT user_fk FOREIGN KEY (user_id) REFERENCES users (id)
)
