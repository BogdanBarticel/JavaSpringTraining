package spring.tutorial.model;

import lombok.Data;

import javax.persistence.*;

@Entity
public @Data
class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne
    private Product product;
    @OneToOne
    private Location location;
    private int quantity;
}
