package spring.tutorial.strategy;

import spring.tutorial.model.Location;

import java.util.Map;

public interface SearchStrategy {

    Location findLocation(Map<Integer, Integer> productQuantity);
}
