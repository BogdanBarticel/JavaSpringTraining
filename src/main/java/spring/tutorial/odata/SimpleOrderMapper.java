package spring.tutorial.odata;

import spring.tutorial.model.Address;
import spring.tutorial.model.pojo.OrderRequest;

import java.util.HashMap;
import java.util.Map;

public class SimpleOrderMapper {

    private SimpleOrderMapper(){}

    public static OrderRequest buildOrderRequest(Map<String, Object> data) {
        OrderRequest or = new OrderRequest();
        or.setCustomer(1);
        Map<Integer, Integer> products = new HashMap<>();
        products.put((int)data.get("Product"), (int)data.get("Quantity"));
        or.setProducts(products);
        Address add = new Address();
        add.setCountry((String)data.get("Country"));
        add.setCounty((String)data.get("County"));
        add.setCity((String)data.get("City"));
        add.setStreet((String)data.get("Street"));
        or.setDestination(add);
        return or;
    }
}
