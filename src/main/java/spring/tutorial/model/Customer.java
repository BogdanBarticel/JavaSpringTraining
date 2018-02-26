package spring.tutorial.model;

import lombok.Data;

import javax.persistence.*;

@Entity
public @Data
class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String firstName;
    private String lastName;
    private String userName;
    @Embedded
    private Address address;
    private String password;
}
