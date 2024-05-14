CREATE TABLE IF NOT EXISTS room (
    roomNumber BIGINT PRIMARY KEY,
    type VARCHAR(255),
    price INT,
    avalaible BOOLEAN
);