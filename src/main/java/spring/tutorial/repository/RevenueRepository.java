package spring.tutorial.repository;

import org.springframework.data.repository.CrudRepository;
import spring.tutorial.model.Revenue;

public interface RevenueRepository extends CrudRepository<Revenue, Long> {


}
