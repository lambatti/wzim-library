DROP TABLE IF EXISTS book_borrows_prolongation_requests;

CREATE TABLE book_borrows_prolongation_requests
(
    user_id           INT          NOT NULL,
    book_slug         VARCHAR(255) NOT NULL,
    request_date      DATE,
    prolongation_date DATE,
    CONSTRAINT book_borrow_prolongation_request_cpk PRIMARY KEY (user_id, book_slug),
    CONSTRAINT user_prolongation_request_fk FOREIGN KEY (user_id) REFERENCES users (id)
)
