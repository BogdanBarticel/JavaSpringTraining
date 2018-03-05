package spring.tutorial.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "order_table")
public @Data
class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long timeStamp;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Location shippedFrom;
    @Embedded
    private Address destination;

}
