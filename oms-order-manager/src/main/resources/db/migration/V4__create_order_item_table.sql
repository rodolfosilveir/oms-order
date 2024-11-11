CREATE TABLE order_item (
    id VARCHAR NOT NULL,
    item_id INT NOT NULL,
    quantity INT NOT NULL CHECK (quantity > 0),
    PRIMARY KEY (id, item_id)
);