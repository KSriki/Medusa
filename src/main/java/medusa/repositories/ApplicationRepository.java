package medusa.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import medusa.models.Application;
import medusa.models.Program;
import medusa.models.User;

public interface ApplicationRepository extends CrudRepository<Application, Long> {

	public long countByProgramAndStatus(Program program, String status);
	public Application findByProgramAndUser(Program program, User user);
	public List<Application> findByProgram(Program program);
	public Application findById(Long id);
	//public User findById(Long id);
	//public User findByApplication(Application application);
}
