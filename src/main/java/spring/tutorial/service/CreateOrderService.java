package spring.tutorial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.tutorial.exception.OrderNotCreatedException;
import spring.tutorial.model.*;
import spring.tutorial.repository.OrderDetailRepository;
import spring.tutorial.repository.OrderRepository;
import spring.tutorial.repository.StockRepository;
import spring.tutorial.util.SearchStrategy;

import java.util.Optional;

@Service
public class CreateOrderService {


    private final OrderRepository orderRep;
    private final OrderDetailRepository detailRep;
    private final StockRepository stockRep;
    private final SearchStrategy locationSearch;

    @Autowired
    public CreateOrderService(OrderRepository orderRep, StockRepository stockRep, SearchStrategy strategy, OrderDetailRepository detailRep) {
        this.orderRep = orderRep;
        this.stockRep = stockRep;
        this.locationSearch = strategy;
        this.detailRep = detailRep;
    }

    public Order createOrder(OrderRequest request) throws OrderNotCreatedException {
        Location loc = locationSearch.findLocation(request);
        updateStock(request.getProduct(), loc, request.getQuantity());


        Order order = new Order();
        order.setShippedFrom(loc);
        order.setDestination(request.getAddress());
        order.setCustomer(request.getCustomer());
        orderRep.save(order);
        OrderDetail detail = new OrderDetail(order, request.getProduct(), request.getQuantity());
        detailRep.save(detail);

        return order;
    }

    private void updateStock(Product product, Location location, int quantity) throws OrderNotCreatedException {
        if (quantity < 1) {
            throw new OrderNotCreatedException();
        }
        Optional<Stock> stock = Optional.ofNullable(stockRep.findByProductAndLocation(product, location));
        if (stock.isPresent()) {
            stock.get().setQuantity(stock.get().getQuantity() - quantity);
            stockRep.save(stock.get());
        }
    }

}
