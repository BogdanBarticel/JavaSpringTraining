package spring.tutorial.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import spring.tutorial.exception.NoStockFoundException;
import spring.tutorial.model.Customer;
import spring.tutorial.model.Location;
import spring.tutorial.model.Product;
import spring.tutorial.model.Stock;
import spring.tutorial.repository.StockRepository;
import spring.tutorial.util.DistanceComparator;
import spring.tutorial.util.GoogleDistanceComparator;

import java.util.ArrayList;
import java.util.List;

public class ClosestLocationSearch implements SearchStrategy {

    private StockRepository stockRep;
    private DistanceComparator comparator;

    @Autowired
    public ClosestLocationSearch(StockRepository stockRep, DistanceComparator comparator) {
        this.stockRep = stockRep;
        this.comparator = comparator;
    }

    @Override
    public Location findLocation(Product product, int quantity, Customer customer) {
        List<Stock> stocks = stockRep.findAllByProductAndQuantityGreaterThan(product, quantity);
        List<Location> locations = new ArrayList<>();
        if(stocks == null || stocks.isEmpty()){
            throw new NoStockFoundException();
        } else {
            for (Stock stock : stocks) {
                locations.add(stock.getLocation());
            }
            return comparator.getClosestLocation(customer.getAddress(), locations);
        }
    }

}
