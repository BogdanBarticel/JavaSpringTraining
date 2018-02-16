package spring.tutorial.model;

import lombok.Data;

import javax.persistence.*;

@Entity
public @Data
class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    private Order order;
    @ManyToOne
    private Product product;
    private int quantity;

    public OrderDetail(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

}
