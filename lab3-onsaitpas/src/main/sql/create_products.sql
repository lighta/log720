DROP TABLE IF EXISTS products;
CREATE TABLE products (
 	id INTEGER NOT NULL PRIMARY KEY,
	description varchar(255),
	price NUMERIC(15,2)
);
CREATE INDEX products_description ON products (description);