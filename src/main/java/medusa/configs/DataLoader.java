//package medusa.configs;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import medusa.models.User;
//import medusa.repositories.UserRepository;
//
//import java.util.Arrays;
//
//
////requires spring.jpa.hibernate.ddl-auto= update or create
//
//@Component
//public class DataLoader implements CommandLineRunner{
//    @Autowired
//    UserRepository userRepository;
//    
//    /*
//    @Autowired
//    RoleRepository roleRepository;
//    @Autowired
//	private PasswordEncoder passwordEncoder;
//    */
//    
//    @Override
//    public void run(String... strings) throws Exception {
//    	
//        System.out.println("Loading data . . .");
//     
//        User user = new User("bob@bob.com","bob","Bob","Bobberson", true, "bob");
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setRoles(Arrays.asList(userRole));
//        userRepository.save(user);
//        user = new User("jim@jim.com","jim","Jim","Jimmerson", true, "jim");
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setRoles(Arrays.asList(userRole));
//        userRepository.save(user);
//        user = new User("admin@secure.com","password","Admin","User", true, "admin");
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setRoles(Arrays.asList(adminRole));
//        userRepository.save(user);
//        user = new User("sam@every.com","password","Sam","Everyman", true, "everyman");
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setRoles(Arrays.asList(userRole, adminRole));
//        userRepository.save(user);
//    }
//    
//    
//}