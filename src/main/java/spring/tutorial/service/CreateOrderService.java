package spring.tutorial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.tutorial.exceptions.OrderNotCreatedException;
import spring.tutorial.model.*;
import spring.tutorial.repository.LocationRepository;
import spring.tutorial.repository.OrderRepository;
import spring.tutorial.repository.StockRepository;
import spring.tutorial.util.SearchStrategy;


@Service
public class CreateOrderService {

    @Autowired
    private OrderRepository orderRep;
    @Autowired
    private LocationRepository locationRep;
    @Autowired
    private StockRepository stockRep;

    private SearchStrategy locationFinder;

    public Order createOrder(OrderRequest request) throws OrderNotCreatedException {
        //CREATE ORDER
        Location loc;
        Order order = new Order();
        if(locationFinder == null) throw new OrderNotCreatedException("SearchStrategy was not set");

        Long locationId = locationFinder.findLocation(request.getProduct(), request.getQuantity(), stockRep);
        if (locationId == null) throw new OrderNotCreatedException("No location could be found");
        loc = locationRep.findOne(locationId);
        order.setShippedFrom(loc.getId());
        order.setDestination(request.getAddress());
        order.setCustomer(request.getCustomer());
        orderRep.save(order);

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
