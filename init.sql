CREATE TABLE users
(
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL
);

CREATE SEQUENCE custom_seq
    START WITH 1
    INCREMENT BY 1;

CREATE TABLE customers
(
    id BIGINT NOT NULL DEFAULT nextval('custom_seq'),
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE orders
(
    id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,
    total_price DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (id)
);


-- Insert some users
INSERT INTO users (first_name, last_name)
VALUES ('John', 'Doe'), ('Jane', 'Doe'), ('Bob', 'Smith');

-- Insert some customers
INSERT INTO customers (name, email, phone)
VALUES ('Acme Inc.', 'info@acme.com', '123-456-7890'), ('XYZ Corp.', 'info@xyz.com', '987-654-3210');

-- Insert some orders
INSERT INTO orders (id, name, total_price)
VALUES (1, 'Order 1', 100.00), (2, 'Order 2', 200.00), (3, 'Order 3', 300.00);
