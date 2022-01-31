CREATE TABLE IF NOT EXISTS car(
    number VARCHAR(20) PRIMARY KEY,
    model VARCHAR(200) NOT NULL,
    year INTEGER NOT NULL,
    color VARCHAR(30) NOT NULL,
    body VARCHAR(10) NOT NULL,
    date TIMESTAMP NOT NULL DEFAULT NOW(),
    CHECK ( body IN ('Truck', 'Sedan', 'Pickup'))
);