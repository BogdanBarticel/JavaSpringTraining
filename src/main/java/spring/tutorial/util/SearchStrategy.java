package spring.tutorial.util;

import spring.tutorial.model.Location;
import spring.tutorial.model.Product;
import spring.tutorial.repository.StockRepository;

public interface SearchStrategy {

    Location findLocation(Product product, int quantity, StockRepository stockRep);
}
