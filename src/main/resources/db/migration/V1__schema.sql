CREATE TABLE product
(
    id bigint(11) NOT NULL AUTO_INCREMENT,
    name varchar(100) NOT NULL,
    description varchar(100) NOT NULL,
    price dec(100) NOT NULL,
    weight double(100) NOT NULL,
    category_id bigint(11) NOT NULL,
    supplier_id bigint(11) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE customer
(
    id bigint(11) NOT NULL AUTO_INCREMENT,
    first_name varchar(100) NOT NULL,
    last_name varchar(100) NOT NULL,
    user_name varchar(100) NOT NULL,
    country varchar(100) NOT NULL,
    county varchar(100) NOT NULL,
    city varchar(100) NOT NULL,
    street varchar(100) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE product_category
(
    id bigint(11) NOT NULL AUTO_INCREMENT,
    name varchar(100) NOT NULL,
    description varchar(100) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE supplier
(
    id bigint(11) NOT NULL AUTO_INCREMENT,
    name varchar(100) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE location
(
    id bigint(11) NOT NULL AUTO_INCREMENT,
    name varchar(100) NOT NULL,
    country varchar(100) NOT NULL,
    county varchar(100) NOT NULL,
    city varchar(100) NOT NULL,
    street varchar(100) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE stock
(
    id bigint(11) NOT NULL AUTO_INCREMENT,
    location_id bigint(11) NOT NULL,
    product_id bigint(11) NOT NULL,
    quantity int(11) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE order_detail
(
    id bigint(11) NOT NULL AUTO_INCREMENT,
    order_id bigint(11) NOT NULL,
    product_id bigint(11) NOT NULL,
    quantity int(11) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE order_list
(
    id bigint(11) NOT NULL AUTO_INCREMENT,
    shipped_from_id bigint(11) NOT NULL,
    customer_id bigint(11) NOT NULL,
    country varchar(100) NOT NULL,
    county varchar(100) NOT NULL,
    city varchar(100) NOT NULL,
    street varchar(100) NOT NULL,
    PRIMARY KEY (id)
);

