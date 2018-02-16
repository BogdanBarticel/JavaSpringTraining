package spring.tutorial.util;

import spring.tutorial.exception.NoStockFoundException;
import spring.tutorial.model.Location;
import spring.tutorial.model.Order;
import spring.tutorial.model.OrderDetail;
import spring.tutorial.repository.OrderDetailRepository;
import spring.tutorial.repository.StockRepository;

import java.util.List;

public class SingleLocationSearch implements SearchStrategy {

    private StockRepository stockRep;
    private OrderDetailRepository detailRep;

    public SingleLocationSearch(StockRepository stockRep, OrderDetailRepository detailRep) {
        this.stockRep = stockRep;
        this.detailRep = detailRep;
    }


    @Override
    public Location findLocation(Order order) {
        Location location = null;
        List<OrderDetail> orderDetails = detailRep.findByOrder(order);
        for (OrderDetail orderDetail : orderDetails) {
            Location thisLocation = stockRep.findByProductAndQuantityGreaterThan(orderDetail.getProduct(), orderDetail.getQuantity()).getLocation();
            if (location == null) {
                location = thisLocation;
            } else if (!thisLocation.equals(location)) {
                throw new NoStockFoundException();
            }
        }
        return location;
    }
}
