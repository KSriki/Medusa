package medusa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import medusa.models.User;
import medusa.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
    UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public void saveUser(User user) {
    	userRepository.save(user);
    }
}
