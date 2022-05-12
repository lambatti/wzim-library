DROP TABLE IF EXISTS book_borrows_prolongation_requests;

CREATE TABLE book_borrows_prolongation_requests
(
    id                SERIAL PRIMARY KEY,
    user_id           INT          NOT NULL,
    book_slug         VARCHAR(255) NOT NULL,
    borrow_date       DATE,
    request_date      DATE,
    prolongation_date DATE,
    CONSTRAINT user_prolongation_request_fk FOREIGN KEY (user_id) REFERENCES users (id)
)
