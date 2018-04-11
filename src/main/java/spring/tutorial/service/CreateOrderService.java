package spring.tutorial.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.tutorial.exception.OrderNotCreatedException;
import spring.tutorial.model.*;
import spring.tutorial.model.pojo.OrderRequest;
import spring.tutorial.repository.*;
import spring.tutorial.strategy.SearchStrategy;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CreateOrderService {


    private final OrderRepository orderRep;
    private final OrderDetailRepository detailRep;
    private final CustomerRepository custRep;
    private final StockRepository stockRep;
    private final ProductRepository prodRep;
    private final SearchStrategy locationSearch;

    @Autowired
    public CreateOrderService(OrderRepository orderRep, OrderDetailRepository detailRep, CustomerRepository custRep, StockRepository stockRep, ProductRepository prodRep, SearchStrategy strategy) {
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
        Customer customer = custRep.findOne((long) request.getCustomer());
        if (request.getDestination() != null && customer != null) {
            order.setCustomer(customer);
            order.setDestination(request.getDestination());
        } else {
            throw new OrderNotCreatedException();
        }
        Date date = new Date();
        order.setTimeStamp(date.getTime());
        orderRep.save(order);

        if (request.getProducts() != null) {
            Map<Product, Integer> products = new HashMap<>();
            for (Map.Entry<Integer, Integer> entry : request.getProducts().entrySet()) {
                Product product = prodRep.findOne(entry.getKey().longValue());
                if (entry.getValue() < 1 || product == null) {
                    throw new OrderNotCreatedException();
                }
                products.put(product, entry.getValue());
            }
            Location shippedFrom;
            if(!products.isEmpty()) {
                shippedFrom = locationSearch.findLocation(products, order.getCustomer());
            }  else throw new OrderNotCreatedException();
            createOrderDetails(products, order, shippedFrom);
        } else {
            throw new OrderNotCreatedException();
        }

        return order;
    }

    private void createOrderDetails(Map<Product, Integer> products, Order order, Location shippedFrom) {
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            OrderDetail detail = new OrderDetail(order, entry.getKey(), entry.getValue(), shippedFrom);
            detailRep.save(detail);
            updateStock(entry.getKey(), detail.getShippedFrom(), entry.getValue());
        }
    }

    private void updateStock(Product product, Location location, int quantity) {
        Stock stock = stockRep.findByProductAndLocation(product, location);
        if (stock != null) {
            stock.setQuantity(stock.getQuantity() - quantity);
            stockRep.save(stock);
        } else {
            throw new OrderNotCreatedException();
        }
    }

    public String orderDetailsToString(Order order) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(om.writeValueAsString(order));
        List<OrderDetail> ods = detailRep.findByOrder(order);
        for (OrderDetail od : ods) {
            sb.append(", ").append(om.writeValueAsString(od));
        }
        sb.append("]");
        return sb.toString();
    }

}
