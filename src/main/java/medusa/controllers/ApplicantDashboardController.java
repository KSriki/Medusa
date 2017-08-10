package medusa.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import medusa.models.Application;
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
	public String browsePrograms(Model model, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		List<Program> programs = programService.programs();
		List<Program> applied = new ArrayList<Program>();
		for (Application app: user.getApplications()) {
			if (!app.getStatus().equals("open")) {
				applied.add(app.getProgram());
			}
		}
		programs.removeAll(applied);
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
	@RequestMapping(value="/dashboard")  //,method=RequestMethod.POST)
	public String showDashboard(Principal principal, Model model) {
		//display user's name on dashboard page apart from
		//model.addAttribute("user", new User());
		User user = userService.findByUsername(principal.getName());
		model.addAttribute("user", user);
		//choose type of user for dashboard here ? with requestmapping for generic "dashboard" ?
		//add selector code
		
		return "dashboard";
	}

	
	@RequestMapping(value="/myprograms", method=RequestMethod.GET)
	public String showMyPrograms(Principal principal, Model model) {
		
		
		User user = userService.findByUsername(principal.getName());

		List<Application> apps = user.getApplications();
		List<Program> progs = new ArrayList<Program>();
		List<Long> counts = new ArrayList<Long>();
		for(Application a : apps) {
			if(!progs.contains(a.getProgram())) {
				progs.add(a.getProgram());
				counts.add(applicationService.countByProgramAndStatus(a.getProgram(), "enrolled"));

			}
			
			counts.add(applicationService.countByProgramAndStatus(a.getProgram(), "enrolled"));
//			if(!mylist.containsKey(a.getProgram())){
//				
//				ArrayList<Application> progApps = new ArrayList<Application>();
//				progApps.add(a);
//				mylist.put(a.getProgram(), progApps);
//				
//			
//			}
//			else { 
//				mylist.get(a.getProgram()).add(a);
//			}
			
		//	size++;
		}
		

		model.addAttribute("apps",apps);
		model.addAttribute("counts",counts);
	//	model.addAttribute("size",size);
	
		return "myprograms";
	}
	
	
}
