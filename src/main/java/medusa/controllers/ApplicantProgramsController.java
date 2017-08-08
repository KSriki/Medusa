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

import medusa.models.Application;
import medusa.models.Program;
import medusa.models.Question;
import medusa.models.Response;
import medusa.models.User;
import medusa.services.ApplicationService;
import medusa.services.ProgramService;
import medusa.services.UserService;

@Controller
public class ApplicantProgramsController {

	@Autowired
	private ProgramService programService;
	@Autowired
	private ApplicationService applicationService;
	@Autowired
	private UserService userService;
//	
//	@RequestMapping(value="/browseprograms", method = RequestMethod.GET)
//	public String browsePrograms(Model model) {
//		List<Program> programs = programService.programs();
//		List<Long> counts = new ArrayList<Long>();
//		for (Program program: programs) {
//			counts.add(applicationService.countByProgramAndStatus(program, "enrolled"));
//		}
//		model.addAttribute("programs", programs);
//		model.addAttribute("counts", counts);
//		return "browseprograms";
//	}
	
	@RequestMapping(value = "/apply", method = RequestMethod.POST)
	public String apply(Model model, @Valid @ModelAttribute("program") Program program, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		Application app = new Application();
		app.setUser(user);
		app.setProgram(program);
		program.addApplication(app);
		user.addApplication(app);
		applicationService.saveApplication(app);
		userService.saveAdmin(user);
		programService.saveProgram(program);
		Program universal = programService.findByName("universal");
		List<Question> uQuestions = universal.getQuestions();
		for (Question question: uQuestions) {
			boolean responseCheck = true;
			for (Response response: question.getResponses()) {
				if (response.getApplication().getUser().equals(user)) {
					responseCheck = false;
					break;
				}
			}
			if (responseCheck) {
				model.addAttribute("question", question);
				
			}
		}
		List<Question> pQuestions = program.getQuestions();
		
		return "apply";
	}
}
