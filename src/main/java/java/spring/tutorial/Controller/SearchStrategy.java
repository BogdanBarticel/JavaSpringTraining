package java.spring.tutorial.Controller;

import java.spring.tutorial.exceptions.LocationNotFoundForProductException;
import java.spring.tutorial.model.StockRepository;

public interface SearchStrategy {

    public Long findLocation(Long product, Long quantity, StockRepository stockRep) throws LocationNotFoundForProductException;
}
