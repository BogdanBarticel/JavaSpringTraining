package spring.tutorial.model;


import lombok.Data;

import javax.persistence.*;

@Entity
public @Data
class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String description;
}
