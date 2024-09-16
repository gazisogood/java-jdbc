CREATE TABLE IF NOT EXISTS users
(
    id       SERIAL PRIMARY KEY,
    login    VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    CONSTRAINT ch_login CHECK (LENGTH(login) BETWEEN 2 AND 50),
    CONSTRAINT ch_password CHECK (LENGTH(password) BETWEEN 8 AND 50)
);

CREATE TABLE IF NOT EXISTS chatroom
(
    id      SERIAL PRIMARY KEY,
    name    VARCHAR(50) NOT NULL,
    creator BIGINT,
    CONSTRAINT fk_chatroom_owner_user_id FOREIGN KEY (creator) REFERENCES users (id),
    CONSTRAINT ch_name CHECK (LENGTH(name) BETWEEN 1 AND 50)
);


CREATE TABLE IF NOT EXISTS message
(
    id        SERIAL PRIMARY KEY,
    author    BIGINT,
    room      BIGINT,
    text      VARCHAR(140),
    date_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_message_user_id FOREIGN KEY (author) REFERENCES users (id),
    CONSTRAINT fk_message_chatroom_id FOREIGN KEY (room) REFERENCES chatroom (id)
);

