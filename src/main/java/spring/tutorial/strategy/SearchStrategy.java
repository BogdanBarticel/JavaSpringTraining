package spring.tutorial.strategy;

import spring.tutorial.model.Customer;
import spring.tutorial.model.Location;
import spring.tutorial.model.Product;

public interface SearchStrategy {

    Location findLocation(Product product, int quantity, Customer customer);
}
