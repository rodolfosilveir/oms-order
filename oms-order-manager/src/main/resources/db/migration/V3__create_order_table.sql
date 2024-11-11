CREATE TABLE "order_details" (
    id VARCHAR PRIMARY KEY,
    occurrence_date TIMESTAMP NOT NULL,
    store VARCHAR(100) NOT NULL,
    status VARCHAR(50) NOT NULL,
    origin_system VARCHAR(50) NOT NULL,
    pickup VARCHAR(20) NOT NULL
);