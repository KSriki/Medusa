package medusa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import medusa.models.Program;
import medusa.models.User;

public interface ProgramRepository extends CrudRepository<Program, Long> {
	public List<Program> findByActive(String active);
	public Program findByName(String name);
	
	//@Query("select p from Program p where ?1 in p.users and active=?2")
	//might have proble,, getting right programs
	public List<Program> findByActiveAndUsersIn(String active,List<User> users);
}
