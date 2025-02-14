CREATE TABLE inventory (
    id SERIAL PRIMARY KEY,
    item_name VARCHAR(50),
    quantity INTEGER DEFAULT 0,
    price INTEGER
);

INSERT INTO inventory (item_name, quantity, price) VALUES
('item_a', 10, 100),
('item_b', 20, 150),
('item_c', 5, 250);