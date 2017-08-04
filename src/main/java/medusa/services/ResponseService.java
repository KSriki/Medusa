package medusa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import medusa.repositories.ResponseRepository;

@Service
public class ResponseService {

	@Autowired
	ResponseRepository responseRepository;
	
}
