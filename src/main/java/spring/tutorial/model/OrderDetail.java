package spring.tutorial.model;

import lombok.Data;

import javax.persistence.*;

@Entity
public @Data
class OrderDetail {

    @Id
    private long id;
    @OneToOne
    private Order order;
    @OneToOne
    private Product product;
    private int quantity;

    public OrderDetail(Order order, Product product, int quantity) {
        this.order = order;
        this.quantity = quantity;
        this.product = product;
    }

}
