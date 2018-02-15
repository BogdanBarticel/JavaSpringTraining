package spring.tutorial.model;


import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public @Data
class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Double weight;
    @ManyToOne
    private ProductCategory category;
    @ManyToOne
    private Supplier supplier;

}
