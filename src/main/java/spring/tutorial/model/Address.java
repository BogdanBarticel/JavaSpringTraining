package spring.tutorial.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public @Data
class Address {
    private String country;
    private String city;
    private String county;
    private String street;


    public String toUrlFormatString() {
        String urlString = country + "," + city + "," + county + "," + street;
        urlString = urlString.replaceAll("\\s+", "+");
        return urlString;
    }

}
