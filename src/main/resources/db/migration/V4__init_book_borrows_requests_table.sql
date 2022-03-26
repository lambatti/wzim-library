DROP TABLE IF EXISTS book_borrows_requests;

CREATE TABLE book_borrows_requests
(
    user_id      INT          NOT NULL,
    book_slug    VARCHAR(255) NOT NULL,
    request_date DATE,
    CONSTRAINT book_borrow_request_cpk PRIMARY KEY (user_id, book_slug),
    CONSTRAINT user_request_fk FOREIGN KEY (user_id) REFERENCES users (id)
)
