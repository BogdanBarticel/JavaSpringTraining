package spring.tutorial.model;

import lombok.Data;

import javax.persistence.*;

@Entity
public @Data
class OrderDetail {

    @Id
    @Column(name="order_id")
    private long order;
    private long product;
    private long quantity;

    public OrderDetail(long order, long product, long quantity){
        this.order = order;
        this.quantity = quantity;
        this.product = product;
    }

}
