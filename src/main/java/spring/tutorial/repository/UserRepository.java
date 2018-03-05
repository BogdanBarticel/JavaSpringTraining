package spring.tutorial.repository;

import org.springframework.data.repository.CrudRepository;
import spring.tutorial.model.User;

public interface UserRepository extends CrudRepository<User, Long>{

    User findByUsername(String username);
}
