package medusa.repositories;

import org.springframework.data.repository.CrudRepository;

import medusa.models.College;

public interface CollegeRepository extends CrudRepository<College, Long> {
	public College findByName(String name);
}
