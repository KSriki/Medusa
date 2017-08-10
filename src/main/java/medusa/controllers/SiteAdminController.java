package medusa.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import medusa.models.Program;
import medusa.models.Question;
import medusa.models.User;
import medusa.repositories.UserRepository;

@Controller 
public class SiteAdminController {

	@Autowired
	UserRepository userRepository;
	
	 @RequestMapping(value="/userlist", method = RequestMethod.GET)
	    public String showApplicantList(Model model) {
		 	List<User> users = userRepository.findAllByRole("APP");
		 	model.addAllAttributes(users);
	    	return "applicantlist";
	    }
	
}
