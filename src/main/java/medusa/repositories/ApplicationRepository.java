package medusa.repositories;

import org.springframework.data.repository.CrudRepository;

import medusa.models.Application;
import medusa.models.Program;
import medusa.models.User;

public interface ApplicationRepository extends CrudRepository<Application, Long> {

	public long countByProgramAndStatus(Program program, String status);
	public Application findByProgramAndUser(Program program, User user);
	public Application findById(Long id);
}
