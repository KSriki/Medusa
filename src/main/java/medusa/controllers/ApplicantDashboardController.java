package medusa.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import medusa.models.Program;
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
		System.out.println(programs);
		for (Program program: programs) {
			counts.add(applicationService.countByProgramAndStatus(program, "enrolled"));
		}
		model.addAttribute("programs", programs);
		model.addAttribute("counts", counts);
		return "browseprograms";
	}
	
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
	
	@RequestMapping(value="/myprograms")
	public String showMyPrograms(Model model) {
		return "myprograms";
	}
	
}
