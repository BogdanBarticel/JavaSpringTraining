package spring.tutorial.util;

import org.springframework.beans.factory.annotation.Autowired;
import spring.tutorial.exception.NoStockFoundException;
import spring.tutorial.model.*;
import spring.tutorial.repository.OrderDetailRepository;
import spring.tutorial.repository.StockRepository;

import java.util.List;
import java.util.Optional;

public class SingleLocationSearch implements SearchStrategy {

    private StockRepository stockRep;
    private OrderDetailRepository detailRep;

    public SingleLocationSearch(StockRepository stockRep, OrderDetailRepository detailRep) {
        this.stockRep = stockRep;
        this.detailRep = detailRep;
    }


    @Override
    public Location findLocation(OrderRequest orderRequest) {
        Stock stock = Optional.ofNullable(stockRep.findByProductAndQuantityGreaterThan(orderRequest.getProduct(), orderRequest.getQuantity())).orElseThrow(NoStockFoundException::new);
        return stock.getLocation();
    }
}
