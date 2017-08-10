package medusa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import medusa.models.College;
import medusa.models.User;
import medusa.repositories.CollegeRepository;

@Service
public class CollegeService {
	
	@Autowired
	CollegeRepository collegeRepository;

	public College findByCollegename(String username){
        return collegeRepository.findByName(username);
    }
	
	public void saveCollege(College c) {
		collegeRepository.save(c);
	}
		
}
