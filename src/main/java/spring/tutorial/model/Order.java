package spring.tutorial.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "orderList")
public @Data
class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    private Location shippedFrom;
    @OneToOne
    private Customer customer;
    @Embedded
    private Address destination;

}
