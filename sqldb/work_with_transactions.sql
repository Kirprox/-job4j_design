CREATE TABLE stock (
    id SERIAL PRIMARY KEY,
    product_name VARCHAR(50),
    manufacturer VARCHAR(50),
    quantity INTEGER DEFAULT 0,
    price INTEGER
);

INSERT INTO stock (product_name, manufacturer, quantity, price)
VALUES
('item_x', 'company_a', 5, 100),
('item_y', 'company_b', 12, 250),
('item_z', 'company_c', 20, 75);