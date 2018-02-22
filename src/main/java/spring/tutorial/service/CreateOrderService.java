package spring.tutorial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.tutorial.exception.OrderNotCreatedException;
import spring.tutorial.model.*;
import spring.tutorial.repository.*;
import spring.tutorial.util.SearchStrategy;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Service
public class CreateOrderService {


    private final OrderRepository orderRep;
    private final OrderDetailRepository detailRep;
    private final CustomerRepository custRep;
    private final StockRepository stockRep;
    private final ProductRepository prodRep;
    private final SearchStrategy locationSearch;

    @Autowired
    public CreateOrderService(OrderRepository orderRep, StockRepository stockRep, SearchStrategy strategy, OrderDetailRepository detailRep, ProductRepository prodRep, CustomerRepository custRep) {
        this.orderRep = orderRep;
        this.stockRep = stockRep;
        this.locationSearch = strategy;
        this.detailRep = detailRep;
        this.prodRep = prodRep;
        this.custRep = custRep;
    }

    @Transactional
    public Order createOrder(OrderRequest request) {
        Order order = new Order();
        order.setShippedFrom(locationSearch.findLocation(request.getProducts()));
        order.setDestination(request.getDestination());
        order.setCustomer(custRep.findOne((long)request.getCustomer()));
        if (request.getTimeStamp() == 0L ){
            Date date = new Date();
            order.setTimeStamp(date.getTime());
        }
        orderRep.save(order);

        for(Map.Entry<Integer, Integer> entry : request.getProducts().entrySet()) {
            Product product = prodRep.findOne(entry.getKey().longValue());
            if (entry.getValue() < 1) {
                throw new OrderNotCreatedException();
            }
            OrderDetail detail = new OrderDetail(order, product, entry.getValue());
            detailRep.save(detail);
            updateStock(product, order.getShippedFrom(), entry.getValue());
        }
        return order;
    }

    private void updateStock(Product product, Location location, int quantity) {
        Optional<Stock> stock = Optional.ofNullable(stockRep.findByProductAndLocation(product, location));
        if (stock.isPresent()) {
            stock.get().setQuantity(stock.get().getQuantity() - quantity);
            stockRep.save(stock.get());
        }
    }

}
