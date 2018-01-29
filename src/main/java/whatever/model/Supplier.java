package whatever.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public @Data
class Supplier {

    @Id
    @SequenceGenerator(name="gen", allocationSize= 1)
    @GeneratedValue(generator="gen")
    private long id;
    private String name;
}
