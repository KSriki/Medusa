package medusa.controllers;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.mail.internet.InternetAddress;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.collect.Lists;

import it.ozimov.springboot.mail.model.Email;
import it.ozimov.springboot.mail.model.defaultimpl.DefaultEmail;
import it.ozimov.springboot.mail.service.EmailService;
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
	@Autowired
	public EmailService emailService;
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
	public String apply(Model model, @Valid @ModelAttribute("program") Program program, Principal principal) throws UnsupportedEncodingException {
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
		if(question==null)
		{
			app.setDateApplied(new Date(System.currentTimeMillis()));
			app.setStatus("applied");
			applicationService.saveApplication(app);
			sendEmailWithoutTemplating_app(user.getUsername(),user.getEmail(),app.getProgram().getName());
			return "completedapplication";
			//return "redirect:/browseprograms";
		}
		model.addAttribute("question", question);
		if (question.getType().equals("MC") || question.getType().equals("MS")) {
			List<String> choices = new ArrayList<String>(Arrays.asList(question.getChoices().split("\\|")));
			model.addAttribute("choices", choices);
		}
		return "apply";
	}
	

	@RequestMapping(value = "/enroll", method = RequestMethod.POST)
	public String enroll(Model model, @Valid @ModelAttribute("app") Application app, Principal principal, BindingResult result) {
//		Application app = applicationService.findById(id);
		//actual application
		Application toEnroll = applicationService.findById(app.getId());
		
		//actual user
		
		User loggedin = userService.findByUsername(principal.getName());
		//should get the user by the application since xss/csrf
	
		User user = toEnroll.getUser();
		if (app == null || loggedin == null || !loggedin.getUsername().equals(user.getUsername())) {
			 return "myprograms";
		}
		
		//actual program
		//Program program = toEnroll.getProgram();
		
		toEnroll.setStatus("enrolled");
	
		applicationService.saveApplication(toEnroll);
		
		model.addAttribute("app", app);
		model.addAttribute("userapp",user);
		return "enroll";
	}
	
	@RequestMapping(value="/next", method=RequestMethod.POST)
	public String next(Model model, @Valid @ModelAttribute("response") Response response, Principal principal) throws UnsupportedEncodingException {
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
			sendEmailWithoutTemplating_app(user.getUsername(),user.getEmail(),app.getProgram().getName());
			return "completedapplication";
		}
	}
	
	public void sendEmailWithoutTemplating_app(String username,String useremail,String program) throws UnsupportedEncodingException{

		  final Email email = DefaultEmail.builder()

		        .from(new InternetAddress("samazon.infosys@gmail.com", "Medusa Admin"))

		        .to(Lists.newArrayList(new InternetAddress(useremail, username)))

		        .subject("Your Application Confirmation in Medusa")

		        .body("Hello "+username+", Thanks for applying in "+program+" program in Medusa Educaion Platform.")

		        .encoding("UTF-8").build();

		  emailService.send(email);

		}
	
	public void sendEmailWithoutTemplating_admin(String username,String useremail) throws UnsupportedEncodingException{

		  final Email email = DefaultEmail.builder()

		        .from(new InternetAddress("samazon.infosys@gmail.com", "Medusa Admin"))

		        .to(Lists.newArrayList(new InternetAddress(useremail, username)))

		        .subject("Your Registration in Medusa")

		        .body("Hello "+username+", Thanks for Registering with Medusa Educaion Platform.")

		        .encoding("UTF-8").build();

		  emailService.send(email);

		}
}
