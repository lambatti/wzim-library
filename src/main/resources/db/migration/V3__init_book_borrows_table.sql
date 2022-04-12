DROP TABLE IF EXISTS book_borrows;

CREATE TABLE book_borrows
(
    id          SERIAL PRIMARY KEY,
    user_id     INT          NOT NULL,
    book_slug   VARCHAR(255) NOT NULL,
    borrow_date DATE,
    return_date DATE,
    CONSTRAINT user_fk FOREIGN KEY (user_id) REFERENCES users (id)
)
