package spring.tutorial.repository;

import org.springframework.data.repository.CrudRepository;
import spring.tutorial.model.ProductCategory;

public interface ProductCategoryRepository extends CrudRepository<ProductCategory, Long> {
}
