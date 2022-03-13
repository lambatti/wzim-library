DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id                          SERIAL PRIMARY KEY,
    first_name                  VARCHAR(20) NOT NULL,
    last_name                   VARCHAR(40) NOT NULL,
    email                       VARCHAR(255) NOT NULL,
    password                    VARCHAR(30) NOT NULL,
    gender                      CHAR(1) NOT NULL,
    security_question           VARCHAR(10) NOT NULL,
    security_question_answer    VARCHAR(30) NOT NULL,
    role                        VARCHAR(10) NOT NULL
)