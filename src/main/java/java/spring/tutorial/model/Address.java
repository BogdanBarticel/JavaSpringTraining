package java.spring.tutorial.model;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
public @Data
class Address {
    private String addressCountry;
    private String addressCity;
    private String addressCounty;
    private String addressStreet;

    public Address(String country, String city, String county, String street){
        addressCity = city;
        addressCountry = country;
        addressCounty = county;
        addressStreet = street;
    }

    public Address() {};

}
