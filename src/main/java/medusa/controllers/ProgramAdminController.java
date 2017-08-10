package medusa.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
		
	}
	
	@RequestMapping(value="/editprogram", method=RequestMethod.POST)
	public String editProg(@Valid @ModelAttribute("program") Program program, Model model) {
		Program prog = programService.findById(program.getId());
		Long count = applicationService.countByProgramAndStatus(prog, "enrolled");
		model.addAttribute("program", prog);
		model.addAttribute("count",count);
		return "editprogram";
		
	}
//	
	@RequestMapping(value="/updateprogram", method=RequestMethod.POST)
	public String updateProg(@Valid @ModelAttribute("program") Program prog, Model model, Principal principal) {
		
		Program program = programService.findById(prog.getId());
		
		program.setStartDate(prog.getStartDate());
		program.setEndDate(prog.getEndDate());
		program.setSchedule(prog.getSchedule());
		program.setDeadline(prog.getDeadline());
		programService.saveProgram(program);
//		
//		Program program = programService.findById(prog.getId());
//		
//		
//		Long count = applicationService.countByProgramAndStatus(prog, "enrolled");
//		model.addAttribute("program", prog);
//		model.addAttribute("count",count);
		
		User user = userService.findByUsername(principal.getName());
		model.addAttribute("user", user);
		
		return "dashboard";
		
	}
//	
//	@RequestMapping(value="/editprogram", method=RequestMethod.POST)
//	public String editProg2(Principal principal, Model model) {
//		
//		User user = userService.findByUsername(principal.getName());
//		List<Program> programs = user.getPrograms();
//		List<Long> counts = new ArrayList<Long>();
//		
//		for (Program program: programs) {
//			counts.add(applicationService.countByProgramAndStatus(program, "enrolled"));
//		}
//		
//		model.addAttribute("counts",counts);
//		model.addAttribute("programs",programs);
//		return "myprogramsp";
//		
//	}

}
