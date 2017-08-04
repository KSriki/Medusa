package medusa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import medusa.model.College;
import medusa.repositories.CollegeRepository;

@Service
public class CollegeService {
	
	@Autowired
	CollegeRepository collegeRepository;

	
	public void saveCollege(College c) {
		collegeRepository.save(c);
	}
		
}
