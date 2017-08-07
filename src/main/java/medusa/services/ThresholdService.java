package medusa.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import medusa.models.Threshold;
import medusa.repositories.ThresholdRepository;

@Service
public class ThresholdService {

	@Autowired
	ThresholdRepository thresholdservice;
	
	public void saveThreshold(Threshold t) {
		thresholdservice.save(t);
	}
	
	
}
