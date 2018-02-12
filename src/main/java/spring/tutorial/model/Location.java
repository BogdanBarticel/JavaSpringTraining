package spring.tutorial.model;

import lombok.Data;

import javax.persistence.*;

@Entity
public @Data
class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    @Embedded
    private Address address;
}
