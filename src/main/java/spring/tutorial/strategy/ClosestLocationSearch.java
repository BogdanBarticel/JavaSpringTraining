package spring.tutorial.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import spring.tutorial.exception.NoStockFoundException;
import spring.tutorial.model.Customer;
import spring.tutorial.model.Location;
import spring.tutorial.model.Product;
import spring.tutorial.model.Stock;
import spring.tutorial.repository.LocationRepository;
import spring.tutorial.repository.StockRepository;
import spring.tutorial.util.DistanceComparator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClosestLocationSearch implements SearchStrategy {

    private LocationRepository locationRep;
    private StockRepository stockRep;
    private DistanceComparator comparator;

    @Autowired
    public ClosestLocationSearch(StockRepository stockRep, DistanceComparator comparator, LocationRepository locationRep) {
        this.stockRep = stockRep;
        this.comparator = comparator;
        this.locationRep = locationRep;
    }

    @Override
    public Location findLocation(Product product, int quantity, Customer customer) {
        List<Stock> stocks = stockRep.findAllByProductAndQuantityGreaterThan(product, quantity);
        List<Location> locations = new ArrayList<>();
        if (stocks == null || stocks.isEmpty()) {
            throw new NoStockFoundException();
        } else {
            for (Stock stock : stocks) {
                locations.add(stock.getLocation());
            }
            return comparator.getClosestLocation(customer.getAddress(), locations);
        }
    }

    @Override
    public Location findLocation(Map<Product, Integer> products, Customer customer) {
        List<Location> locations = locationRep.findAll();
        List<Stock> stocks = new ArrayList<>();
        for(Location location : locations){
            for (Map.Entry<Product, Integer> entry : products.entrySet()) {
                stocks = stockRep.findAllByProductAndLocationAndQuantityGreaterThan(entry.getKey(), location, entry.getValue());
            }
        }

        if (stocks == null || stocks.isEmpty()) {
            throw new NoStockFoundException();
        } else {
            for (Stock stock : stocks) {
                locations.add(stock.getLocation());
            }
            return comparator.getClosestLocation(customer.getAddress(), locations);
        }
    }

}
