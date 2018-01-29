package whatever.model;

import lombok.Data;

import javax.persistence.Entity;


public @Data
class Stock {


    private long product;
    private long location;
    private long quantity;
}
