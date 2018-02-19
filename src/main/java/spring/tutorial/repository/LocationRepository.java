package spring.tutorial.repository;

import org.springframework.data.repository.CrudRepository;
import spring.tutorial.model.Location;

import java.util.List;

public interface LocationRepository extends CrudRepository<Location, Long> {

    List<Location> findAll();

    Location findById(long id);
}
