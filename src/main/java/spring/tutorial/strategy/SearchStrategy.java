package spring.tutorial.strategy;

import spring.tutorial.model.Customer;
import spring.tutorial.model.Location;
import spring.tutorial.model.Product;

import java.util.Map;

public interface SearchStrategy {

    Location findLocation(Product product, int quantity, Customer customer);

    Location findLocation(Map<Product,Integer> products, Customer customer);
}
