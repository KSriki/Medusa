package medusa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import medusa.models.Application;
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
}
