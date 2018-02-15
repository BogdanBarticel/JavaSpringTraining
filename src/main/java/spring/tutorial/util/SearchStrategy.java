package spring.tutorial.util;

import spring.tutorial.model.Location;
import spring.tutorial.model.Order;
import spring.tutorial.model.OrderRequest;
import spring.tutorial.model.Product;
import spring.tutorial.repository.StockRepository;

import java.util.List;

public interface SearchStrategy {

    Location findLocation(OrderRequest requests);
}
