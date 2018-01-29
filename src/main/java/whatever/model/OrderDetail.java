package whatever.model;

import lombok.Data;

import javax.persistence.Entity;


public @Data
class OrderDetail {
    private long order;
    private long product;
    private long quantity;
}
