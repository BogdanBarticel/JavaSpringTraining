package spring.tutorial.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.tutorial.exceptions.LocationNotFoundForProductException;
import spring.tutorial.exceptions.OrderNotCreatedException;
import spring.tutorial.model.*;
import whatever.model.*;

import java.spring.tutorial.model.*;


@Service
public class CreateOrderService {

    @Autowired
    private OrderRepository orderRep;
    @Autowired
    private LocationRepository locationRep;
    @Autowired
    private StockRepository stockRep;

    private SearchStrategy locationFinder;

    public void setStrategy(SearchStrategy strategy){
        locationFinder = strategy;
    }

    public Order createOrder(OrderRequest request) throws OrderNotCreatedException, LocationNotFoundForProductException {
        //CREATE ORDER
        Location loc;
        Order order = new Order();
        if(locationFinder == null) throw new OrderNotCreatedException("SearchStrategy was not set");
        try {
            Long locationId = locationFinder.findLocation(request.getProduct(), request.getQuantity(), stockRep);
            if (locationId == null) throw new OrderNotCreatedException("No location could be found");
            loc = locationRep.findOne(locationId);
            order.setShippedFrom(loc.getId());
            order.setDestination(request.getAddress());
            order.setCustomer(request.getCustomer());
            orderRep.save(order);
        } catch (LocationNotFoundForProductException ex) { throw ex;}

        //CREATE ORDER_DETAIL
        OrderDetail detail = new OrderDetail();
        detail.setProduct(request.getProduct());
        detail.setQuantity(request.getQuantity());
        detail.setOrder(order.getId());

        //UPDATE STOCK
        Stock stock = stockRep.findByProductAndLocation(detail.getProduct(), order.getShippedFrom());
        if(stock != null){
            stock.setQuantity(stock.getQuantity() - detail.getQuantity());
            stockRep.save(stock);
        }
        return order;
    }
}
