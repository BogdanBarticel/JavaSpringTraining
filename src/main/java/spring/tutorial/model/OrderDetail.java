package spring.tutorial.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public @Data
class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    private Order order;
    @ManyToOne
    private Product product;
    @ManyToOne
    private Location shippedFrom;
    private int quantity;

    public OrderDetail(Order order, Product product, int quantity, Location shippedFrom) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.shippedFrom = shippedFrom;
    }

}
