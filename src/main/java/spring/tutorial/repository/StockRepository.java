package spring.tutorial.repository;

import org.springframework.data.repository.CrudRepository;
import spring.tutorial.model.Location;
import spring.tutorial.model.Product;
import spring.tutorial.model.Stock;

import java.util.List;

public interface StockRepository extends CrudRepository<Stock, Long> {

    Stock findByProductAndQuantityGreaterThan(Product product, int quantity);

    List<Stock> findAllByProductAndQuantityGreaterThan(Product product, int quantity);

    List<Stock> findAllByProductAndLocationAndQuantityGreaterThan(Product product, Location location, int quantity);

    List<Stock> findByLocation(int locationId);

    Stock findByProductAndLocation(Product product, Location location);

    List<Stock> findByProduct(Product product);

    List<Stock> findAll();
}
