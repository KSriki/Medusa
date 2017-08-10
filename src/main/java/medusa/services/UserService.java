package medusa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import medusa.models.User;
import medusa.repositories.UserRepository;
import medusa.services.SSUserDetailsService;

@Service
public class UserService {
	@Autowired
    UserRepository userRepository;
    //@Autowired
   // private PasswordEncoder passwordEncoder;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public Long countByEmail(String email) {
        return userRepository.countByEmail(email);
    }
    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }
    public void saveUser(User user) {
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
    	//System.out.println(user.getPassword());
    	//StandardPBEStringEncryptor spbe= new StandardPBEStringEncryptor();
        //spbe.setPassword(user.getPassword());
        //user.setPassword(spbe.encrypt(user.getPassword()));
        user.setActive("true");
        userRepository.save(user);
    }
    public void saveAdmin(User user) {
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
    	//StandardPBEStringEncryptor spbe= new StandardPBEStringEncryptor();
       // spbe.setPassword(user.getPassword());
        user.setActive("true");
        userRepository.save(user);
    }
    
    public List<User> findByRoleAndActive(String role, String active) {
    	return userRepository.findByRoleAndActive(role, active);
    }
    
    /*public boolean checkpassword(String raw_pass,String encrypted_pass)
    {
    	return passwordEncoder.matches(raw_pass,encrypted_pass);
    }*/
    public SSUserDetailsService createservice()  
    {
    	return new SSUserDetailsService(userRepository);
    }

    
    public List<User> findallByRole(String role){
    	return userRepository.findAllByRole(role);
    }
}