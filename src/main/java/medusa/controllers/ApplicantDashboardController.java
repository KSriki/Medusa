package medusa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import medusa.services.UserService;


@Controller
public class ApplicantDashboardController {

	@Autowired
	private UserService userService;
	
	//method is post for when we get specific user from form after logging in
	@RequestMapping(value="/applicantdashboard")  //,method=RequestMethod.POST)
	public String showDashboard(Model model) {
		//display user's name on dashboard page apart from
		//model.addAttribute("user", new User());
		
		//choose type of user for dashboard here ? with requestmapping for generic "dashboard" ?
		//add selector code
		
		return "dashboard";
	}


	@RequestMapping(value="/myprofile")
	public String showProfile(Model model) {
		return "myprofile";
	}
	
	@RequestMapping(value="/browseprograms")
	public String showPrograms(Model model) {
		return "browseprograms";
	}
	
	@RequestMapping(value="/myprograms")
	public String showMyPrograms(Model model) {
		return "myprograms";
	}
	
}
