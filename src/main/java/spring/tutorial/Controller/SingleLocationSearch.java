package spring.tutorial.Controller;

import spring.tutorial.exceptions.LocationNotFoundForProductException;
import whatever.model.*;

import spring.tutorial.model.Stock;
import spring.tutorial.model.StockRepository;
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
