package whatever;

import lombok.Data;

public @Data
class Order {
    private int id;
    private int shippedFrom;
    private int customer;
    private String addressCountry;
    private String addressCity;
    private String addressCounty;
    private String addressStreet;

}
