package spring.tutorial.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="orderList")
public @Data
class Order {

    @Id
    @SequenceGenerator(name = "gen", allocationSize= 1)
    @GeneratedValue(generator = "gen")
    private long id;
    private long shippedFrom;
    private long customer;
    @Embedded
    private Address destination;

}
