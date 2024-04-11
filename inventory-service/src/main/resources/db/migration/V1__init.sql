CREATE TABLE inventories (
    id SERIAL PRIMARY KEY NOT NULL,
    sku_code VARCHAR(255) NOT NULL,
    quantity INT NOT NULL
);