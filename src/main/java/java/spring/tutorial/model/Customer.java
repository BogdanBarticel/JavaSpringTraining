package java.spring.tutorial.model;

import lombok.Data;

import javax.persistence.*;

@Entity
public @Data
class Customer {

    @Id
    @SequenceGenerator(name = "gen", allocationSize= 1)
    @GeneratedValue(generator = "gen")
    private long id;
    private String firstName;
    private String lastName;
    private String userName;
    @Embedded
    private Address address;
}
