CREATE TABLE Product
(
    id bigint(11) NOT NULL AUTO_INCREMENT,
    name varchar(100) NOT NULL,
    description varchar(100) NOT NULL,
    price dec(100) NOT NULL,
    weight double(100) NOT NULL,
    category bigint(11) NOT NULL,
    supplier bigint(11) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE customer
(
    id bigint(11) NOT NULL AUTO_INCREMENT,
    first_name varchar(100) NOT NULL,
    last_name varchar(100) NOT NULL,
    user_name varchar(100) NOT NULL,
    address_country varchar(100) NOT NULL,
    address_county varchar(100) NOT NULL,
    address_city varchar(100) NOT NULL,
    address_street varchar(100) NOT NULL,
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
    address_country varchar(100) NOT NULL,
    address_county varchar(100) NOT NULL,
    address_city varchar(100) NOT NULL,
    address_street varchar(100) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE stock
(
    location bigint(11) NOT NULL,
    product bigint(100) NOT NULL,
    quantity bigint(11) NOT NULL,
    PRIMARY KEY (location, product)
);

CREATE TABLE order_detail
(
    order_id bigint(11) NOT NULL,
    product bigint(100) NOT NULL,
    quantity bigint(100) NOT NULL,
    PRIMARY KEY (order_id)
);


CREATE TABLE order_list
(
    id bigint(11) NOT NULL AUTO_INCREMENT,
    shipped_from bigint(100) NOT NULL,
    customer bigint(100) NOT NULL,
    address_country varchar(100) NOT NULL,
    address_county varchar(100) NOT NULL,
    address_city varchar(100) NOT NULL,
    address_street varchar(100) NOT NULL,
    PRIMARY KEY (id)
);

