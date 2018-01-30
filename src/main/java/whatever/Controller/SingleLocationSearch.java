package whatever.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import whatever.model.*;

import java.util.List;

public class SingleLocationSearch implements SearchStrategy {

    @Autowired
    StockRepository stockRep;

    @Override
    public Long findLocation(OrderDetail orderDetail) {
        List<Stock> stocks = stockRep.findByProductAndQuantityGreaterThan(orderDetail.getProduct(), orderDetail.getQuantity());
        if(!stocks.isEmpty()) return stocks.get(0).getLocation();
        return null;
    }
}
