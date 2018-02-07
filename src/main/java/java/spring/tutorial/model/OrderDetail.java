package java.spring.tutorial.model;

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
}
