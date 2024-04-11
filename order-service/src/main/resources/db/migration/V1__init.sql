CREATE TABLE orders (
    id SERIAL PRIMARY KEY NOT NULL,
    order_number VARCHAR(255) NOT NULL,
    sku_code VARCHAR(255) NOT NULL,
    price decimal(19,2) NOT NULL,
    quantity INT NOT NULL
);