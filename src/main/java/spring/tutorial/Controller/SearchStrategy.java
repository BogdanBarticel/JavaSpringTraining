package spring.tutorial.Controller;

import spring.tutorial.exceptions.LocationNotFoundForProductException;
import spring.tutorial.model.StockRepository;

public interface SearchStrategy {

    public Long findLocation(Long product, Long quantity, StockRepository stockRep) throws LocationNotFoundForProductException;
}
