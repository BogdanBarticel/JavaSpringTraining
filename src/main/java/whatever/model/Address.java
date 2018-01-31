package whatever.model;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
public @Data
class Address {
    private String addressCountry;
    private String addressCity;
    private String addressCounty;
    private String addressStreet;
}
