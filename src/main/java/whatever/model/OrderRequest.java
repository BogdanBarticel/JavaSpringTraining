package whatever.model;

import lombok.Data;

public @Data
class OrderRequest {

    private long customer;
    private long product;
    private long quantity;
    private Address address;
}
