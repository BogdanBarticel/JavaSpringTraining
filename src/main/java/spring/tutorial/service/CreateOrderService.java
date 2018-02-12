package spring.tutorial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.tutorial.exceptions.OrderNotCreatedException;
import spring.tutorial.model.*;
import spring.tutorial.repository.OrderRepository;
import spring.tutorial.repository.StockRepository;
import spring.tutorial.util.SearchStrategy;

@Service
public class CreateOrderService {


    private final OrderRepository orderRep;
    private final StockRepository stockRep;
    private final SearchStrategy locationSearch;

    @Autowired
    public CreateOrderService(OrderRepository orderRep, StockRepository stockRep, SearchStrategy strategy) {
        this.orderRep = orderRep;
        this.stockRep = stockRep;
        this.locationSearch = strategy;
    }

    public Order createOrder(OrderRequest request) throws OrderNotCreatedException {
        Order order = new Order();

        Location loc = locationSearch.findLocation(request.getProduct(), request.getQuantity(), stockRep);
        if (loc == null) {
            throw new OrderNotCreatedException("No location could be found");
        }

        order.setShippedFrom(loc);
        order.setDestination(request.getAddress());
        order.setCustomer(request.getCustomer());

        orderRep.save(order);
        OrderDetail detail = new OrderDetail(order, request.getProduct(), request.getQuantity());
        updateStock(detail.getProduct(), order.getShippedFrom(), detail.getQuantity());
        return order;
    }

    private void updateStock(Product product, Location location, int quantity){
        Stock stock = stockRep.findByProductAndLocation(product.getId(), location.getId());
        if(stock != null){
            stock.setQuantity(stock.getQuantity() - quantity);
            stockRep.save(stock);
        }
    }

}
