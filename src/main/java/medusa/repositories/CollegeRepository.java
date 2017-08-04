package medusa.repositories;

import org.springframework.data.repository.CrudRepository;

import medusa.model.College;

public interface CollegeRepository extends CrudRepository<College, Long> {

}
