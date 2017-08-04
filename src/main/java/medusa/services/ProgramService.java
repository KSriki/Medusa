package medusa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import medusa.models.Program;
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
}
