CREATE TABLE Product
(
    id int(11) NOT NULL AUTO_INCREMENT,
    name varchar(100) NOT NULL,
    description varchar(100) NOT NULL,
    price dec(100) NOT NULL,
    weight double(100) NOT NULL,
    category int(11) NOT NULL,
    supplier int(11) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE ProductCategory
(
    id int(11) NOT NULL AUTO_INCREMENT,
    name varchar(100) NOT NULL,
    description varchar(100) NOT NULL,
    PRIMARY KEY (id)
);