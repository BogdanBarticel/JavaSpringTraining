package java.spring.tutorial.Controller;

import java.spring.tutorial.exceptions.LocationNotFoundForProductException;
import whatever.model.*;

import java.spring.tutorial.model.Stock;
import java.spring.tutorial.model.StockRepository;
import java.util.List;

public class SingleLocationSearch implements SearchStrategy {


    @Override
    public Long findLocation(Long product, Long quantity, StockRepository stockRep) throws LocationNotFoundForProductException {
        List<Stock> stocks = stockRep.findByProductAndQuantityGreaterThan(product, quantity);
        System.err.println(stocks.toString());
        if(stocks.isEmpty()) {
            throw new LocationNotFoundForProductException("No location was found that contains "+quantity+" of " + product);
        }
        return stocks.get(0).getLocation();

    }
}
