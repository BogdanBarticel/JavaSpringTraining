package spring.tutorial.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Revenue {

    public Revenue(Location location, Date date, BigInteger sum){
        this.location = location;
        this.date = date;
        this.sum = sum;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    private Location location;
    private Date date;
    private BigInteger sum;

}
