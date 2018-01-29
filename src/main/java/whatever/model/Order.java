package whatever.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;


public @Data
class Order {

    @Id
    @SequenceGenerator(name = "gen", allocationSize= 1)
    @GeneratedValue(generator = "gen")
    private long id;
    private long shippedFrom;
    private long customer;
    private String addressCountry;
    private String addressCity;
    private String addressCounty;
    private String addressStreet;

}
