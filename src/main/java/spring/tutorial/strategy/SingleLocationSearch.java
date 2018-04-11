package spring.tutorial.strategy;

import spring.tutorial.exception.NoStockFoundException;
import spring.tutorial.model.Customer;
import spring.tutorial.model.Location;
import spring.tutorial.model.Product;
import spring.tutorial.model.Stock;
import spring.tutorial.repository.StockRepository;

import java.util.Map;

public class SingleLocationSearch implements SearchStrategy {

    private StockRepository stockRep;

    public SingleLocationSearch(StockRepository stockRep) {
        this.stockRep = stockRep;
    }


    @Override
    public Location findLocation(Product product, int quantity, Customer customer) {
        Location location;
        Stock stock = stockRep.findByProductAndQuantityGreaterThan(product, quantity);
        if (stock != null) {
            location = stock.getLocation();
        } else {
            throw new NoStockFoundException();
        }
        return location;
    }

    @Override
    public Location findLocation(Map<Product, Integer> products, Customer customer) {
        Stock stock = null;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            stock = stockRep.findByProductAndQuantityGreaterThan(entry.getKey(), entry.getValue());
        }
        Location location;
        if (stock != null) {
            location = stock.getLocation();
        } else {
            throw new NoStockFoundException();
        }
        return location;
    }
}
