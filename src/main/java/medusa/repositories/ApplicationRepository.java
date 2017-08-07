package medusa.repositories;

import org.springframework.data.repository.CrudRepository;

import medusa.models.Application;
import medusa.models.Program;

public interface ApplicationRepository extends CrudRepository<Application, Long> {

	public long countByProgramAndStatus(Program program, String status);
}
