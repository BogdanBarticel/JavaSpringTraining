package spring.tutorial.repository;

import org.springframework.data.repository.CrudRepository;
import spring.tutorial.model.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
