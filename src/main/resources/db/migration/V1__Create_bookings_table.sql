CREATE TABLE bookings (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    total INT NOT NULL,
    message VARCHAR(255) NOT NULL
);
