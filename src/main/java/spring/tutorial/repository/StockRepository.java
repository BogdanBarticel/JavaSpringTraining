package spring.tutorial.repository;

import org.springframework.data.repository.CrudRepository;
import spring.tutorial.model.Location;
import spring.tutorial.model.Product;
import spring.tutorial.model.Stock;

import java.util.List;

public interface StockRepository extends CrudRepository<Stock, Long> {

    Stock findByProductAndQuantityGreaterThan(Product product, int Quantity);

    List<Stock> findByLocation(Location location);

    Stock findByProductAndLocation(Product product, Location Location);

}
