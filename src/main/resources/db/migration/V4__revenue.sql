CREATE TABLE revenue
(
    id bigint(11) NOT NULL AUTO_INCREMENT,
    location_id bigint(11) NOT NULL,
    date timestamp NOT NULL,
    sum dec(10) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (location_id) REFERENCES location(id)
);