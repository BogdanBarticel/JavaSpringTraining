package spring.tutorial.repository;

import org.springframework.data.repository.CrudRepository;
import spring.tutorial.model.Stock;

import java.util.List;

public interface StockRepository extends CrudRepository<Stock, Long>{

    List<Stock> findByProductAndQuantityGreaterThan(Long product, Long Quantity);
    List<Stock> findByLocation(long locationId);
    Stock findByProductAndLocation(Long product, Long Location);

}
