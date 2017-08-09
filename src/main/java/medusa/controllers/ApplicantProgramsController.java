package medusa.controllers;

import java.security.Principal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
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
import medusa.services.QuestionService;
import medusa.services.ResponseService;
import medusa.services.UserService;

@Controller
public class ApplicantProgramsController {

	@Autowired
	private ProgramService programService;
	@Autowired
	private ApplicationService applicationService;
	@Autowired
	private UserService userService;
	@Autowired
	private ResponseService responseService;
	@Autowired
	private QuestionService questionService;
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
	
	private Question findNextQuestion(Program program, User user) {
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
				
				return question;
			}
		}
		List<Question> pQuestions = program.getQuestions();
		for (Question question: pQuestions) {
			boolean responseCheck = true;
			for (Response response: question.getResponses()) {
				if (response.getApplication().getUser().equals(user)) {
					responseCheck = false;
					break;
				}
			}
			if (responseCheck) {
				return question;
			}
		}
		return null;
	}
	
	@RequestMapping(value = "/apply", method = RequestMethod.POST)
	public String apply(Model model, @Valid @ModelAttribute("program") Program program, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		model.addAttribute("programName", program.getName());
		model.addAttribute("response", new Response());
		Application app = applicationService.findByProgramAndUser(program, user);
		if (app == null) {
			app = new Application();
			app.setUser(user);
			app.setProgram(program);
			program.addApplication(app);
			user.addApplication(app);
			applicationService.saveApplication(app);
			userService.saveAdmin(user);
			programService.saveProgram(program);
		}
		model.addAttribute("app", app);
		Question question = findNextQuestion(program, user);
		model.addAttribute("question", question);
		if (question.getType().equals("MC") || question.getType().equals("MS")) {
			List<String> choices = new ArrayList<String>(Arrays.asList(question.getChoices().split("\\|")));
			model.addAttribute("choices", choices);
		}
		return "apply";
	}
	
	@RequestMapping(name="/next", method=RequestMethod.POST)
	public String next(Model model, @Valid @ModelAttribute("response") Response response, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		Application app = response.getApplication();
		app.addResponse(response);
		Question q = response.getQuestion();
		q.addResponse(response);
		responseService.saveResponse(response);
		questionService.saveQuestion(q);
		model.addAttribute("programName", app.getProgram().getName());
		model.addAttribute("response", new Response());
		model.addAttribute("app", app);
		Question question = findNextQuestion(app.getProgram(), user);
		if (question != null) {
			model.addAttribute("question", question);
			applicationService.saveApplication(app);
			if (question.getType().equals("MC") || question.getType().equals("MS")) {
				List<String> choices = new ArrayList<String>(Arrays.asList(question.getChoices().split("\\|")));
				model.addAttribute("choices", choices);
			}
			return "apply";
		}
		else {
			app.setDateApplied(new Date(System.currentTimeMillis()));
			app.setStatus("applied");
			applicationService.saveApplication(app);
			return "completedapplication";
		}
	}
}
