package spring.tutorial.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.tutorial.model.Address;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
public @Data
class OrderRequest {

    private int customer;
    private long timeStamp;
    private Map<Integer, Integer> products;
    private Address destination;

}
