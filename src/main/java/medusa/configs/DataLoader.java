package medusa.configs;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import medusa.models.College;
import medusa.models.Program;
import medusa.models.User;
import medusa.repositories.CollegeRepository;
import medusa.repositories.ProgramRepository;
import medusa.repositories.UserRepository;
@Component
public class DataLoader implements CommandLineRunner{
    @Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    CollegeRepository collegeRepository;
    @Autowired
    ProgramRepository programRepository;
    @SuppressWarnings("deprecation")
	@Override
   public void run(String... strings) throws Exception {
        System.out.println("Loading data . . .");
//        User user = new User("bob@bob.com","bob","Bob","Bobberson", "bob", "APP");
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        userRepository.save(user);
//        User user2 = new User("jim@jim.com","jim","Jim","Jimmerson", "jim", "PADMIN");
//        user2.setPassword(passwordEncoder.encode(user2.getPassword()));
//        userRepository.save(user2);
//        User user3 = new User("admin@secure.com","password","Admin","User", "admin", "SADMIN");
//        user3.setPassword(passwordEncoder.encode(user3.getPassword()));
//        userRepository.save(user3);
//        College college1 = new College("Montgomery College");
//        College college2 = new College("Frederick College");
//        collegeRepository.save(college1);
//        collegeRepository.save(college2);
//        Program universal = new Program();
//        universal.setName("universal");
//        universal.setCollege(college1);
//        programRepository.save(universal);
//        college1.addProgram(universal);
//        collegeRepository.save(college1);
//    	College college1 = collegeRepository.findByName("Montgomery College");
//    	College college2 = collegeRepository.findByName("Frederick College");
//    	User user2 = userRepository.findByUsername("jim");
//        Program program = new Program("Java Developer", "Complete Java developer training for beginners", new Date(2017,7,31), new Date(2017,8,30), new Date(2017,7,15), 30, "10:00 AM - 12:00 PM\nMWF");
//        program.setCollege(college1);
//        program.addUser(user2);
//        
//        programRepository.save(program);
//        user2.addProgram(program);
//        college1.addProgram(program);
//        userRepository.save(user2);
//        System.out.println(college1.getPrograms());
//        collegeRepository.save(college1);
//        Program program3 = new Program("Cryptography", "Basics of cryptography and encryption including hashing and public/private key pairs", new Date(2017,7,31), new Date(2017,8,30), new Date(2017,7,15), 20, "10:00 AM - 4:00 PM\nTR");
//        program3.setCollege(college1);
//        program3.addUser(user2);
//       
//        
//        programRepository.save(program3);
//        college1.addProgram(program3);
//        user2.addProgram(program3);
//        System.out.println(program3.getId());
//        userRepository.save(user2);
//        collegeRepository.save(college1);
//        Program program2 = new Program("Networking Protocols", "Computer networking protocols - hands on training", new Date(2017,7,31), new Date(2017,8,30), new Date(2017,7,15), 20, "9:00 AM - 10:00 PM\nMWF");
//        program2.setCollege(college2);
//        program2.addUser(user2);
//        user2.addProgram(program2);
//        college2.addProgram(program2);
//        programRepository.save(program2);
//        userRepository.save(user2);
//        collegeRepository.save(college2);
    }
}