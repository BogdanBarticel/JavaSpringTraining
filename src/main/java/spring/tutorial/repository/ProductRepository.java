package spring.tutorial.repository;

import org.springframework.data.repository.CrudRepository;
import spring.tutorial.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {


}
