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

@Controller
public class ApplicantProgramsController {

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
}
