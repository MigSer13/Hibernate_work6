DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (id bigint PRIMARY KEY, title VARCHAR(255), price int);
INSERT INTO products (title, price) VALUES
('tomato', 17),
('apple', 13),
('bread', 40);

DROP TABLE IF EXISTS customers CASCADE;
CREATE TABLE customers (id bigint PRIMARY KEY, title VARCHAR(255));
INSERT INTO customers (title) VALUES
('Modern'),
('Sprut'),
('Porto'),
('Dorina');

DROP TABLE IF EXISTS products_customer CASCADE;
CREATE TABLE products_customer (product_id bigint, customer_id bigint, FOREIGN KEY (product_id) REFERENCES products (id), FOREIGN KEY (customer_id) REFERENCES customers (id));
INSERT INTO products_customer (product_id, customer_id) VALUES
(1, 1),
(2, 1),
(2, 2),
(3, 2),
(3, 3);