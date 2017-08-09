package medusa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import medusa.models.Application;
import medusa.models.Program;
import medusa.models.User;
import medusa.repositories.ApplicationRepository;

@Service
public class ApplicationService {
	@Autowired
    ApplicationRepository applicationRepository;
    @Autowired
    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }
    
    public void saveApplication(Application application) {
    	applicationRepository.save(application);
    }
    
    public long countByProgramAndStatus(Program program, String status) {
    	return applicationRepository.countByProgramAndStatus(program, status);
    }
    
    public Application findByProgramAndUser(Program program, User user) {
    	return applicationRepository.findByProgramAndUser(program, user);
    }
    
    public Application findById(Long id) {
    	return applicationRepository.findById(id);
    }

}
