package spring.tutorial.repository;

import org.springframework.data.repository.CrudRepository;
import spring.tutorial.model.Order;
import spring.tutorial.model.OrderDetail;

import java.util.List;

public interface OrderDetailRepository extends CrudRepository<OrderDetail, Long> {

    List<OrderDetail> findByOrder(Order order);
}
