CREATE TABLE import_order (
    id SERIAL PRIMARY KEY,
    order_id VARCHAR(50) NOT NULL,
    occurrence_date TIMESTAMP NOT NULL,
    import_status VARCHAR(50) NOT NULL,
    import_attempts INTEGER NOT NULL,
    request_body TEXT NOT NULL
);