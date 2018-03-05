
insert into user_table(username, password) values ('user1', '$2a$10$SXBL8lMZmHlRn5sFLs8KA.jVRACebG01zBaiLs0OurJBX0eIdVnJ2');
insert into user_table(username, password) values ('user2', '$2a$10$SXBL8lMZmHlRn5sFLs8KA.jVRACebG01zBaiLs0OurJBX0eIdVnJ2');
insert into user_table(username, password) values ('user3', '$2a$10$SXBL8lMZmHlRn5sFLs8KA.jVRACebG01zBaiLs0OurJBX0eIdVnJ2');
insert into user_role (user_id, role) values (1, 'ROLE_ADMIN');
insert into user_role (user_id, role) values (2, 'ROLE_CUSTOMER');
insert into user_role (user_id, role) values (3, 'ROLE_ADMIN');
insert into user_role (user_id, role) values (3, 'ROLE_CUSTOMER');
insert into customer (first_name, last_name, country, county, city, street, user_id) values ('Maricica', 'Petronela', 'Romania', 'Vaslui', 'Vaslui', 'Str. Baltii nr. 9', 1);
insert into customer (first_name, last_name, country, county, city, street, user_id) values ('Marinel', 'Marcel', 'Romania', 'Vaslui', 'Vaslui', 'Str. Baltii nr. 9', 2);

