package whatever.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import whatever.model.*;

import java.util.List;

public class SingleLocationSearch implements SearchStrategy {

    @Autowired
    StockRepository stockRep;

    @Override
    public Long findLocation(Long product, Long quantity) {
        List<Stock> stocks = stockRep.findByProductAndQuantityGreaterThan(product, quantity);
        if(!stocks.isEmpty()) return stocks.get(0).getLocation();
        return null;
    }
}
