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
        User user = new User("bob@bob.com","bob","Bob","Bobberson", "bob", "APP");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        user = new User("jim@jim.com","jim","Jim","Jimmerson", "jim", "PADMIN");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        user = new User("admin@secure.com","password","Admin","User", "admin", "SADMIN");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        College college1 = new College("Montgomery College");
        College college2 = new College("Frederick College");
        collegeRepository.save(college1);
        collegeRepository.save(college2);
        Program program = new Program("Java Developer", "Complete Java developer training for beginners", new Date(2017,7,31), new Date(2017,8,30), new Date(2017,7,15), 30, "10:00 AM - 12:00 PM\nMWF");
        program.setCollege(college1);
        college1.addProgram(program);
        programRepository.save(program);
        collegeRepository.save(college1);
        program = new Program("Java Developer", "Complete Java developer training for beginners", new Date(2017,7,31), new Date(2017,8,30), new Date(2017,7,15), 20, "10:00 AM - 4:00 PM\nTR");
        program.setCollege(college1);
        college1.addProgram(program);
        programRepository.save(program);
        collegeRepository.save(college1);
        program = new Program("Networking Protocols", "Computer networking protocols - hands on training", new Date(2017,7,31), new Date(2017,8,30), new Date(2017,7,15), 20, "9:00 AM - 10:00 PM\nMWF");
        program.setCollege(college1);
        college1.addProgram(program);
        programRepository.save(program);
        collegeRepository.save(college1);
    }
}