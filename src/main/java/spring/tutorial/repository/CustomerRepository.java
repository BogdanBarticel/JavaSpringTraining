package spring.tutorial.repository;

import org.springframework.data.repository.CrudRepository;
import spring.tutorial.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
