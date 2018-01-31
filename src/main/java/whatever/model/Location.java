package whatever.model;

import lombok.Data;

import javax.persistence.*;

@Entity
public @Data
class Location {

    @Id
    @SequenceGenerator(name = "gen", allocationSize= 1)
    @GeneratedValue(generator = "gen")
    private long id;
    private String name;
    @Embedded
    private Address address;
}
