package spring.tutorial.model;

import lombok.Data;

public @Data
class OrderRequest {

    private long customer;
    private long product;
    private long quantity;
    private Address address;

    public OrderRequest(long customer, long product, long quantity, Address address){
        this.customer = customer;
        this.product = product;
        this.quantity = quantity;
        this.address = address;
    }
}
