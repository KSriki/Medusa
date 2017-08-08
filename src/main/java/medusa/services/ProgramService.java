package medusa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import medusa.models.Program;
import medusa.models.User;
import medusa.repositories.ProgramRepository;

@Service
public class ProgramService {
	@Autowired
    ProgramRepository programRepository;
    @Autowired
    public ProgramService(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }
    
    public void saveProgram(Program program) {
    	programRepository.save(program);
    }
    
    public List<Program> programs() {
    	return programRepository.findByActive("true");
    }
    
    public List<Program> findByActiveAndUsersIn(String active,List<User> users){
		return programRepository.findByActiveAndUsersIn(active, users);
    	
    	
    }
    
//    public List<Program> getProgramsByUser(User u) {
//    	
//    	
//    	List<Program> userprog =  programRepository.findActiveAndUsersIn("true", u);
//    	
//    	
//    	
//    	
//		return userprog;
//    }
    
    
    public Program findByName(String name) {
    	return programRepository.findByName(name);
    }
    
    public Program findById(long id) {
    	return programRepository.findById(id);
    }
}
