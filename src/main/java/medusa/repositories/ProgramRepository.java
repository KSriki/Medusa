package medusa.repositories;

import org.springframework.data.repository.CrudRepository;

import medusa.models.Program;

public interface ProgramRepository extends CrudRepository<Program, Long> {

}
