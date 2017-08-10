package medusa.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import medusa.models.Application;
import medusa.models.College;
import medusa.models.Program;
import medusa.models.Question;
import medusa.models.User;
import medusa.services.ApplicationService;
import medusa.services.CollegeService;
import medusa.services.ProgramService;
import medusa.services.QuestionService;
import medusa.services.UserService;

@Controller 
public class ProgramAdminController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private ProgramService programService;
	@Autowired
	private ApplicationService applicationService;
	@Autowired 
	private CollegeService collegeService;
	@Autowired
	private QuestionService questionService;

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
	
	@RequestMapping(value="/updateprogram", method=RequestMethod.POST)
	public String updateProg(@Valid @ModelAttribute("program") Program program, Model model, Principal principal) {
		
		Program prog = programService.findById(program.getId());
		
		prog.setStartDate(program.getStartDate());
		prog.setEndDate(program.getEndDate());
		prog.setSchedule(program.getSchedule());
		prog.setDeadline(program.getDeadline());
		programService.saveProgram(prog);

		User user = userService.findByUsername(principal.getName());
		model.addAttribute("user", user);
		
		return "dashboard";
		
	}	
	
	@RequestMapping(value="/addsession", method=RequestMethod.POST)
	public String addSession(@Valid @ModelAttribute("program") Program program, Model model) {
		
		Program prog = programService.findById(program.getId());
		Long count = applicationService.countByProgramAndStatus(prog, "enrolled");
		
		model.addAttribute("program",prog);
		model.addAttribute("newProgram",new Program());
		model.addAttribute("count",count);

		return "addsession";
	}
	
	@RequestMapping(value="/addedsession", method=RequestMethod.POST)
	public String addedsession(@Valid @ModelAttribute("newProgram") Program newProgram, Model model, Principal principal) {
		//program holds values coming in from user's inputs via form
		//prog is the existing base program in the database
		//newProgram is the new program to be added to the database
		Program oldProg = programService.findFirstByName(newProgram.getName());
		
		System.out.println(oldProg.getCollege());
		newProgram.setCollege(oldProg.getCollege());
		newProgram.setClassSize(oldProg.getClassSize());
		newProgram.setDescription(oldProg.getDescription());
		
		
		List<Question> quest = oldProg.getQuestions();
		Iterator<Question> iter = quest.iterator();
		
		List<User> users = oldProg.getUsers();
		Iterator<User> iter2 = users.iterator();
		
		while(iter.hasNext()) {
			Question q = iter.next();
			newProgram.addQuestion(q);
		}
		
		while (iter2.hasNext()) {
			newProgram.addUser(iter2.next());
		}
		
		programService.saveProgram(newProgram);
		
		iter = quest.iterator();
		while (iter.hasNext()) {
			Question q = iter.next();
			q.addProgram(newProgram);
			questionService.saveQuestion(q);
		}
		
		iter2 = users.iterator();
		
		while (iter2.hasNext()) {
			User u = iter2.next();
			u.addProgram(newProgram);
			userService.saveUser(u);
		}
		
		College college = oldProg.getCollege();
		college.addProgram(newProgram);
		
		collegeService.saveCollege(college);
//		
//		List<Question> quest = oldProg.getQuestions();
//		Iterator<Question> iter = quest.iterator();
//		while(iter.hasNext()) {
//			Question q = iter.next();
//			q.addProgram(newProgram);
//			questionService.saveQuestion(q);
//		}
//		
//		Program prog = programService.findById(program.getId());
//		
//		newProgram.setActive("true");
//		newProgram.setClassSize(program.getClassSize());
//		newProgram.setCollege(program.getCollege());
//		newProgram.setDeadline(prog.getDeadline());
//		newProgram.setDescription(program.getDescription());
//		newProgram.setEndDate(prog.getEndDate());
//		newProgram.setName(program.getName());
//		newProgram.setQuestions(program.getQuestions());
//		newProgram.setSchedule(prog.getSchedule());
//		newProgram.setStartDate(prog.getStartDate());
//
//		programService.saveProgram(newProgram);
//		
//		College college = program.getCollege();
//		college.addProgram(newProgram);
//		
//		List<Question> quest = program.getQuestions();
//		Iterator<Question> iter = quest.iterator();
//		while(iter.hasNext()) {
//			Question q = iter.next();
//			q.addProgram(newProgram);
//			questionService.saveQuestion(q);
//		}
//		
//		collegeService.saveCollege(college);
//		
		User user = userService.findByUsername(principal.getName());
		model.addAttribute("user", user);
		
		return "dashboard";
		
	}	
	
	@RequestMapping(value="/viewallapplicants", method=RequestMethod.GET)
	public String viewAllApps(Principal principal, Model model) {
		
		User user = userService.findByUsername(principal.getName());
		
		List<User> list_users = new ArrayList<User>();
		
		List<Program> program_list = user.getPrograms();
		
		for (Program p : program_list) {
			System.out.println(p.getName());
			List<Application> app_list = p.getApplications();
			for (Application a : app_list) {
				System.out.println(a.getUser());
				list_users.add(a.getUser());
			}
		}
		
		model.addAttribute("users",list_users);
		
		return "viewallapplicants_pa";
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
