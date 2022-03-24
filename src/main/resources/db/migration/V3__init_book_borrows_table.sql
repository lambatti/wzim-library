DROP TABLE IF EXISTS book_borrows;

CREATE TABLE book_borrows
(
    user_id     INT,
    book_slug   VARCHAR(255),
    borrow_date DATE,
    return_date DATE,
    CONSTRAINT user_fk FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT book_borrow_cpk PRIMARY KEY (user_id, book_slug)
)