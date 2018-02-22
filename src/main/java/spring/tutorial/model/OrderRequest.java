package spring.tutorial.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.ManyToOne;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
public @Data
class OrderRequest {

    private int customer;
    private long timeStamp;
    @ManyToOne
    private Map<Integer, Integer> products;
    @Embedded
    private Address destination;

}
