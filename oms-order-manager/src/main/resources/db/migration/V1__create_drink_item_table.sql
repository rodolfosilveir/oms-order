CREATE TABLE item (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    category VARCHAR(50) NOT NULL,
    volume_mililiters INTEGER NOT NULL,
    price DECIMAL(10, 2) NOT NULL
);