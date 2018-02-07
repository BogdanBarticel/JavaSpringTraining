package spring.tutorial.repository;

import org.springframework.data.repository.CrudRepository;
import spring.tutorial.model.Supplier;

public interface SupplierRepository extends CrudRepository<Supplier, Long> {
}
