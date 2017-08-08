package medusa.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import medusa.models.Program;
import medusa.models.User;
import medusa.services.ApplicationService;
import medusa.services.ProgramService;
import medusa.services.UserService;


@Controller
public class ApplicantDashboardController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ProgramService programService;
	@Autowired
	private ApplicationService applicationService;
	
	@RequestMapping(value="/browseprograms", method = RequestMethod.GET)
	public String browsePrograms(Model model) {
		List<Program> programs = programService.programs();
		List<Long> counts = new ArrayList<Long>();
		for (Program program: programs) {
			counts.add(applicationService.countByProgramAndStatus(program, "enrolled"));
		}
		model.addAttribute("programs", programs);
		model.addAttribute("counts", counts);
		return "browseprograms";
	}
	
	//method is post for when we get specific user from form after logging in
	@RequestMapping(value="/applicantdashboard")  //,method=RequestMethod.POST)
	public String showDashboard(Principal principal, Model model) {
		//display user's name on dashboard page apart from
		//model.addAttribute("user", new User());
		User user = userService.findByUsername(principal.getName());
		model.addAttribute("user", user);
		//choose type of user for dashboard here ? with requestmapping for generic "dashboard" ?
		//add selector code
		
		return "dashboard";
	}


	@RequestMapping(value="/myprofile", method= RequestMethod.GET)
	public String showProfile(Principal principal,Model model) {
		User user = userService.findByUsername(principal.getName());
		model.addAttribute("user", user);
		return "myprofile";
	}
	


	@RequestMapping(value="/myprofile", method= RequestMethod.POST)
	public String editProfile(Principal principal,Model model) {
		
		
		
		User user = userService.findByUsername(principal.getName());
		model.addAttribute("user", user);
		return "myprofile";
	}
	
	
	@RequestMapping(value="/myprograms")
	public String showMyPrograms(Model model) {
		return "myprograms";
	}
	
}
