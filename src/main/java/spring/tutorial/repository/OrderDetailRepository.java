package spring.tutorial.repository;

import org.springframework.data.repository.CrudRepository;
import spring.tutorial.model.Order;
import spring.tutorial.model.OrderDetail;

public interface OrderDetailRepository extends CrudRepository<OrderDetail, Long> {

    OrderDetail findByOrder(Order order);
}
