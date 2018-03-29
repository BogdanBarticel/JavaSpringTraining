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

CREATE TABLE product
(
    id bigint(11) NOT NULL AUTO_INCREMENT,
    name varchar(100) NOT NULL,
    description varchar(100) NOT NULL,
    price double(100) NOT NULL,
    weight double(100) NOT NULL,
    category_id bigint(11) NOT NULL,
    supplier_id bigint(11) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (category_id) REFERENCES product_category(id),
    FOREIGN KEY (supplier_id) REFERENCES supplier(id)
);

CREATE TABLE stock
(
    id bigint(11) NOT NULL AUTO_INCREMENT,
    location_id bigint(11) NOT NULL,
    product_id bigint(11) NOT NULL,
    quantity int(11) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (product_id) REFERENCES product(id),
    FOREIGN KEY (location_id) REFERENCES location(id)
);

CREATE TABLE user_table
(
    id bigint(11) NOT NULL AUTO_INCREMENT,
    username varchar(100) NOT NULL,
    password varchar(100) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE user_role
(
    id bigint(11) NOT NULL AUTO_INCREMENT,
    user_id bigint(11) NOT NULL,
    role varchar(100) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY (role, user_id),
    FOREIGN KEY (user_id) REFERENCES user_table (id)
);

CREATE TABLE customer
(
    id bigint(11) NOT NULL AUTO_INCREMENT,
    first_name varchar(100) NOT NULL,
    last_name varchar(100) NOT NULL,
    country varchar(100) NOT NULL,
    county varchar(100) NOT NULL,
    city varchar(100) NOT NULL,
    street varchar(100) NOT NULL,
    user_id bigint(11) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY (id, user_id),
    FOREIGN KEY (user_id) REFERENCES user_table(id)
);

CREATE TABLE order_table
(
    id bigint(11) NOT NULL AUTO_INCREMENT,
    time_stamp bigint(100) NOT NULL,
    customer_id bigint(11) NOT NULL,
    country varchar(100) NOT NULL,
    county varchar(100) NOT NULL,
    city varchar(100) NOT NULL,
    street varchar(100) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (customer_id) REFERENCES customer(id),

);

CREATE TABLE order_detail
(
    id bigint(11) NOT NULL AUTO_INCREMENT,
    order_id bigint(11) NOT NULL,
    product_id bigint(11) NOT NULL,
    shipped_from_id bigint(11) NOT NULL,
    quantity int(11) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (order_id) REFERENCES order_table(id),
    FOREIGN KEY (shipped_from_id) REFERENCES location(id)
);

