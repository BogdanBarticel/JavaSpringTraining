package spring.tutorial.repository;

import org.springframework.data.repository.CrudRepository;
import spring.tutorial.model.Stock;

import java.util.List;

public interface StockRepository extends CrudRepository<Stock, Long>{

    List<Stock> findByProductAndQuantityGreaterThan(Long product, Long Quantity);
    List<Stock> findByLocationId(long locationId);
    Stock findByProductAndLocation(long product, long Location);

}
