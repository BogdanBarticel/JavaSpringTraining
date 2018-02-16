package spring.tutorial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.tutorial.exception.OrderNotCreatedException;
import spring.tutorial.model.*;
import spring.tutorial.repository.OrderDetailRepository;
import spring.tutorial.repository.OrderRepository;
import spring.tutorial.repository.StockRepository;
import spring.tutorial.util.SearchStrategy;

import java.util.Iterator;
import java.util.Map;
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

    @Transactional
    public Order createOrder(OrderRequest request) {

        Iterator it = request.getProducts().entrySet().iterator();
        Order order = new Order();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            Product prod = (Product) pair.getKey();
            int quantity = (int) pair.getValue();
            if (quantity < 1) {
                throw new OrderNotCreatedException();
            }
            OrderDetail detail = new OrderDetail(prod, quantity);
            detail.setOrder(order);
            detailRep.save(detail);
        }

        order.setShippedFrom(locationSearch.findLocation(order));
        order.setDestination(request.getDestination());
        order.setCustomer(request.getCustomer());
        orderRep.save(order);
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
