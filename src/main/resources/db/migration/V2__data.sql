insert into product_category (name, description) values ('Auto', 'Automobile');
insert into product_category (name, description) values ('Moto', 'Motorcycle');

insert into supplier (name) values ('Volkswagen Automotive Group');
insert into supplier (name) values ('Yamaha Motorsports');

insert into location (name, address_country, address_city, address_county, address_street) values ('Depo No 1', 'Romania', 'Iasi', 'Iasi', 'Bul Poitiers nr. 11');
insert into location (name, address_country, address_city, address_county, address_street) values ('Depo No 2', 'Romania', 'Bucuresti', 'Ilfov', 'Calea Eroilor nr 1');
insert into location (name, address_country, address_city, address_county, address_street) values ('Depo No 3', 'Romania', 'Cluj-Napoca', 'Cluj', 'Str. Samuel Brassai nr. 6');

insert into stock (location_id, product_id, quantity) values (1,1, 10);
insert into stock (location_id, product_id, quantity) values (1,2, 20);
insert into stock (location_id, product_id, quantity) values (1,3, 50);
insert into stock (location_id, product_id, quantity) values (1,4, 10);
insert into stock (location_id, product_id, quantity) values (2,5, 10);
insert into stock (location_id, product_id, quantity) values (3,6, 11);
insert into stock (location_id, product_id, quantity) values (3,7, 2);
insert into stock (location_id, product_id, quantity) values (2,8, 10);

insert into product(name, description, price, weight, category_id, supplier_id) values ('Golf', '1.6 FSI', 10500,1.383,1,1);
insert into product(name, description, price, weight, category_id, supplier_id) values ('Polo', '1.2 MPI', 7800,1.008,1,1);
insert into product(name, description, price, weight, category_id, supplier_id) values ('Fabia', '1.9 TDI', 9800,1.118, 1,1);
insert into product(name, description, price, weight, category_id, supplier_id) values ('Octavia', '2.0 TDI', 17200,1.601,1,1);
insert into product(name, description, price, weight, category_id, supplier_id) values ('Superb', '1.4 TDI', 21300,1.545,1,1);
insert into product(name, description, price, weight, category_id, supplier_id) values ('YYZ', '1.8 ZYM', 12000, 0.15,2,2);
insert into product(name, description, price, weight, category_id, supplier_id) values ('C550', '2.0 v4',24000, 0.2,2,2);
insert into product(name, description, price, weight, category_id, supplier_id) values ('Q7', '3.0 BlueMotion', 34000, 2.103,1,1);

