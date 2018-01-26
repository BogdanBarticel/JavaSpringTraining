package whatever;


import lombok.Data;

import java.math.BigDecimal;

public @Data
class Product {

    private long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Double weight;
    private int category;
    private int supplier;

}
