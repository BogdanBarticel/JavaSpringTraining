package spring.tutorial.repository;

import org.springframework.data.repository.CrudRepository;
import spring.tutorial.model.UserRole;

import java.util.List;

public interface UserRoleRepository extends CrudRepository<UserRole, Long> {

   public List<UserRole> findUserRoleByUserId(Long userId);
}
