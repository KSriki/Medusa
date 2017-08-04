package medusa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import medusa.models.Response;
import medusa.repositories.ResponseRepository;

@Service
public class ResponseService {

	@Autowired
	ResponseRepository responseRepository;
	
	public void saveResponse(Response r) {
		responseRepository.save(r);
	}
	
	
}
