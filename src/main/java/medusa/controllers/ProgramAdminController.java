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
public class ProgramAdminController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private ProgramService programService;
	@Autowired
	private ApplicationService applicationService;

	@RequestMapping(value="/browseprogramsp", method=RequestMethod.GET)
	public String browsePrograms(Model model) {
		List<Program> programs = programService.programs();
		List<Long> counts = new ArrayList<Long>();
		System.out.println(programs);
		for (Program program: programs) {
			counts.add(applicationService.countByProgramAndStatus(program, "enrolled"));
		}
		model.addAttribute("programs", programs);
		model.addAttribute("counts", counts);
		return "browseprogramsp";
	}
	
	@RequestMapping(value="/myprogramsp", method=RequestMethod.GET)
	public String myPrograms(Principal principal, Model model) {
		
		User user = userService.findByUsername(principal.getName());
		List<Program> programs = user.getPrograms();
		List<Long> counts = new ArrayList<Long>();
		
		for (Program program: programs) {
			counts.add(applicationService.countByProgramAndStatus(program, "enrolled"));
		}
		
		model.addAttribute("counts",counts);
		model.addAttribute("programs",programs);
		return "myprogramsp";
		
		
		
//		List<Long> counts = new ArrayList<Long>();
//		
//		System.out.println(programs);
//		
//		for (Program program: programs) {
//			counts.add(applicationService.countByProgramAndStatus(program, "enrolled"));
//		}
//		
//		model.addAttribute("programs", programs);
//		List<Program> programs = programService.programs();
//		model.addAttribute("counts", counts);
//		return "browseprogramsp";
		
	}

}
