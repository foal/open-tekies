CREATE SEQUENCE customer_seq
	INCREMENT BY 50
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1;

CREATE SEQUENCE item_seq
	INCREMENT BY 50
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1;

CREATE SEQUENCE order_seq
	INCREMENT BY 50
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1;

CREATE TABLE customer (
	id int8 NOT NULL,
	birthday date NULL,
	email varchar NULL,
	firstname varchar NULL,
	lastname varchar NULL,
	notes varchar NULL,
	phone varchar NULL,
	CONSTRAINT customer_pkey PRIMARY KEY (id),
	CONSTRAINT customer_un UNIQUE (email)
);

CREATE TABLE item (
	id int8 NOT NULL,
	description varchar NULL,
	"name" varchar NULL,
	price numeric NULL,
	vat numeric NULL,
	CONSTRAINT item_pkey PRIMARY KEY (id)
);

CREATE TABLE itemorder (
	id int8 NOT NULL,
	"comment" varchar NULL,
	createtime timestamp NULL,
	customer_id int8 NULL,
	CONSTRAINT itemorder_pkey PRIMARY KEY (id),
	CONSTRAINT itemorder_fk FOREIGN KEY (customer_id) REFERENCES customer(id)
);

CREATE TABLE order_reservations (
	item_order int8 NOT NULL,
	amount int4 NOT NULL,
	discount numeric NULL,
	item_id int8 NOT NULL,
	price numeric NULL,
	vat numeric NULL,
	"position" int4 NOT NULL,
	CONSTRAINT order_reservations_pkey PRIMARY KEY (item_order, "position"),
	CONSTRAINT order_reservations_order_fk FOREIGN KEY (item_order) REFERENCES itemorder(id),
	CONSTRAINT order_reservations_item_fk FOREIGN KEY (item_id) REFERENCES item(id)
);


