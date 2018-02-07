package java.spring.tutorial.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public @Data
class Stock {

    @Id
    private long product;
    private long location;
    private long quantity;
}
