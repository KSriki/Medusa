package medusa.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import medusa.models.Program;

public interface ProgramRepository extends CrudRepository<Program, Long> {
	public List<Program> findByActive(String active);
	public Program findByName(String name);
	public Program findById(long id);
}
