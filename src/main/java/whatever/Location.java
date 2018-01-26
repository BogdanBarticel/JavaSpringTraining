package whatever;

import lombok.Data;

public @Data
class Location {
    private int id;
    private String name;
    private String addressCountry;
    private String addressCity;
    private String addressCounty;
    private String addressStreet;
}
