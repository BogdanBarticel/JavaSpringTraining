package whatever.model;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StockRepository extends CrudRepository<Stock, Long>{

    List<Stock> findByProductAndQuantityGreaterThan(Long product, Long Quantity);
}
