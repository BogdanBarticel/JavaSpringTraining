package spring.tutorial.model;

import lombok.Data;

import javax.persistence.Embedded;
import javax.persistence.OneToOne;

public @Data
class OrderRequest {

    private Customer customer;
    @OneToOne
    private Product product;
    private int quantity;
    @Embedded
    private Address address;

    private OrderRequest() {
    }

    public OrderRequest(Customer customer, Product product, int quantity, Address address) {
        this.customer = customer;
        this.product = product;
        this.quantity = quantity;
        this.address = address;
    }
}
