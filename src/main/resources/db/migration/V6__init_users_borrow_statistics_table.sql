DROP TABLE IF EXISTS users_borrow_statistics;

CREATE TABLE users_borrow_statistics
(
    user_id        SERIAL NOT NULL,
    borrowed_books INT,
    read_books     INT,
    CONSTRAINT user_borrow_statistics_fk FOREIGN KEY (user_id) REFERENCES users (id)
)
