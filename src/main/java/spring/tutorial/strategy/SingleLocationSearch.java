package spring.tutorial.strategy;

import spring.tutorial.exception.NoStockFoundException;
import spring.tutorial.model.Location;
import spring.tutorial.model.Product;
import spring.tutorial.repository.ProductRepository;
import spring.tutorial.repository.StockRepository;

import java.util.Map;

public class SingleLocationSearch implements SearchStrategy {

    private StockRepository stockRep;
    private ProductRepository prodRep;

    public SingleLocationSearch(StockRepository stockRep, ProductRepository prodRep) {
        this.stockRep = stockRep;
        this.prodRep = prodRep;
    }


    @Override
    public Location findLocation(Map<Integer, Integer> productQuantity) {
        Location location = null;
        for (Map.Entry<Integer, Integer> orderDetail : productQuantity.entrySet()) {
            Product product = prodRep.findOne((long)orderDetail.getKey());
            Location thisLocation = stockRep.findByProductAndQuantityGreaterThan(product, orderDetail.getValue()).getLocation();
            if (location == null) {
                location = thisLocation;
            } else if (!thisLocation.equals(location)) {
                throw new NoStockFoundException();
            }
        }
        return location;
    }
}
