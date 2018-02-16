package spring.tutorial.util;

import spring.tutorial.model.Location;
import spring.tutorial.model.Order;

public interface SearchStrategy {

    Location findLocation(Order order);
}
