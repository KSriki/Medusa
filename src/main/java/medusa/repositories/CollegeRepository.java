package medusa.repositories;

import org.springframework.data.repository.CrudRepository;

import medusa.models.College;
import medusa.models.User;

public interface CollegeRepository extends CrudRepository<College, Long> {
	public College findByName(String name);
	//public College findByname(String username);
}
