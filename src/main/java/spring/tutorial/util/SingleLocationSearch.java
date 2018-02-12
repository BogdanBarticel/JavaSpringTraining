package spring.tutorial.util;

import spring.tutorial.model.Location;
import spring.tutorial.model.Product;
import spring.tutorial.model.Stock;
import spring.tutorial.repository.StockRepository;
import java.util.List;

public class SingleLocationSearch implements SearchStrategy {


    @Override
    public Location findLocation(Product product, int quantity, StockRepository stockRep) {
        List<Stock> stocks = stockRep.findByProductAndQuantityGreaterThan(product.getId(), (long)quantity);
        System.err.println(stocks.toString());
        if(stocks.isEmpty()) return null;
        return stocks.get(0).getLocation();

    }
}
