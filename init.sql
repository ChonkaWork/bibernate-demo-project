CREATE TABLE users
(
    id         BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL
);

CREATE SEQUENCE custom_seq
    START WITH 1
    INCREMENT BY 1;

CREATE TABLE customers
(
    id    BIGINT       NOT NULL DEFAULT nextval('custom_seq'),
    name  VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE orders
(
    id          BIGINT         NOT NULL,
    name        VARCHAR(255)   NOT NULL,
    total_price DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE persons
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    team VARCHAR(255) NOT NULL
);

CREATE TABLE notes
(
    id        BIGSERIAL PRIMARY KEY,
    person_id BIGINT NOT NULL REFERENCES persons (id),
    body      TEXT   NOT NULL
);

-- Insert some Person entities
INSERT INTO persons (name, team)
VALUES ('John Doe', 'Team A');
INSERT INTO persons (name, team)
VALUES ('Jane Doe', 'Team B');
INSERT INTO persons (name, team)
VALUES ('Bob Smith', 'Team C');

INSERT INTO notes (person_id, body)
VALUES (1, 'Meeting at 10am on Monday');
INSERT INTO notes (person_id, body)
VALUES (1, 'Remember to bring your laptop');
INSERT INTO notes (person_id, body)
VALUES (1, 'Donuts in the break room!');

INSERT INTO notes (person_id, body)
VALUES (2, 'Call client to reschedule meeting');
INSERT INTO notes (person_id, body)
VALUES (2, 'Finish proposal by Friday');
INSERT INTO notes (person_id, body)
VALUES (2, 'Order new office supplies');

INSERT INTO notes (person_id, body)
VALUES (3, 'Complete project milestone by end of week');
INSERT INTO notes (person_id, body)
VALUES (3, 'Schedule team meeting for next Monday');

INSERT INTO users (first_name, last_name)
VALUES ('John', 'Doe'),
       ('Jane', 'Doe'),
       ('Bob', 'Smith');

INSERT INTO customers (name, email, phone)
VALUES ('Acme Inc.', 'info@acme.com', '123-456-7890'),
       ('XYZ Corp.', 'info@xyz.com', '987-654-3210');

INSERT INTO orders (id, name, total_price)
VALUES (1, 'Order 1', 100.00),
       (2, 'Order 2', 200.00),
       (3, 'Order 3', 300.00),
       (999, 'Default order', 999.00);
