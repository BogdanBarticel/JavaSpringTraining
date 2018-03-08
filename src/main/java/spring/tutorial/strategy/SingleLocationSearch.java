package spring.tutorial.strategy;

import spring.tutorial.exception.NoStockFoundException;
import spring.tutorial.model.Customer;
import spring.tutorial.model.Location;
import spring.tutorial.model.Product;
import spring.tutorial.repository.StockRepository;

public class SingleLocationSearch implements SearchStrategy {

    private StockRepository stockRep;

    public SingleLocationSearch(StockRepository stockRep) {
        this.stockRep = stockRep;
    }


    @Override
    public Location findLocation(Product product, int quantity, Customer customer) {
        Location location;
        Location thisLocation = stockRep.findByProductAndQuantityGreaterThan(product, quantity).getLocation();
        if (thisLocation != null) {
            location = thisLocation;
        } else {
            throw new NoStockFoundException();
        }
        return location;
    }
}
