package spring.tutorial.repository;

import org.springframework.data.repository.CrudRepository;
import spring.tutorial.model.Location;

public interface LocationRepository extends CrudRepository<Location, Long> {

    Location findById(long id);
}
