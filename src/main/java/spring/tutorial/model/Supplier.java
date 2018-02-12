package spring.tutorial.model;

import lombok.Data;

import javax.persistence.*;

@Entity
public @Data
class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
}
