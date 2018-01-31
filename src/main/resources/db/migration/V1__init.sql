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

insert into product_category (name, description) values ('Auto', 'Automobile');
insert into product_category (name, description) values ('Moto', 'Motorcycle');

insert into supplier (name) values ('Volkswagen Automotive Group');
insert into supplier (name) values ('Yamaha Motorsports');

insert into location (name, address_country, address_city, address_county, address_street) values ('Depo No 1', 'Romania', 'Iasi', 'Iasi', 'Bul Poitiers nr. 11');
insert into location (name, address_country, address_city, address_county, address_street) values ('Depo No 2', 'Romania', 'Bucuresti', 'Ilfov', 'Calea Eroilor nr 1');
insert into location (name, address_country, address_city, address_county, address_street) values ('Depo No 3', 'Romania', 'Cluj-Napoca', 'Cluj', 'Str. Samuel Brassai nr. 6');

insert into stock (location, product, quantity) values (1,1, 10);
insert into stock (location, product, quantity) values (1,2, 20);
insert into stock (location, product, quantity) values (1,3, 50);
insert into stock (location, product, quantity) values (1,4, 10);
insert into stock (location, product, quantity) values (2,5, 10);
insert into stock (location, product, quantity) values (3,6, 11);
insert into stock (location, product, quantity) values (3,7, 2);
insert into stock (location, product, quantity) values (2,8, 10);

insert into product(name, description, price, weight, category, supplier) values ('Golf', '1.6 FSI', 10500,1.383,1,1);
insert into product(name, description, price, weight, category, supplier) values ('Polo', '1.2 MPI', 7800,1.008,1,1);
insert into product(name, description, price, weight, category, supplier) values ('Fabia', '1.9 TDI', 9800,1.118, 1,1);
insert into product(name, description, price, weight, category, supplier) values ('Octavia', '2.0 TDI', 17200,1.601,1,1);
insert into product(name, description, price, weight, category, supplier) values ('Superb', '1.4 TDI', 21300,1.545,1,1);
insert into product(name, description, price, weight, category, supplier) values ('YYZ', '1.8 ZYM', 12000, 0.15,2,2);
insert into product(name, description, price, weight, category, supplier) values ('C550', '2.0 v4',24000, 0.2,2,2);
insert into product(name, description, price, weight, category, supplier) values ('Q7', '3.0 BlueMotion', 34000, 2.103,1,1);

