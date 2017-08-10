package medusa.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import medusa.models.Application;
import medusa.models.College;
import medusa.models.Program;
import medusa.models.User;
import medusa.repositories.CollegeRepository;
import medusa.repositories.UserRepository;
import medusa.services.ApplicationService;
import medusa.services.CollegeService;
import medusa.services.ProgramService;
import medusa.services.UserService;


@Controller
public class SystemAdminController {

	
	@Autowired
	private CollegeService collegeService;
	@Autowired
	private UserService userService;
	@Autowired
	private ProgramService programService;
	@Autowired
	private ApplicationService applicationService;
   /* @Autowired
    CollegeRepository collegeRepository;
    @Autowired
    UserRepository userRepository;
	*/
	@RequestMapping(value="/viewallapplicants_college", method=RequestMethod.GET)
	public String viewapplicants(Principal principal, Model model) {
		
		User user = userService.findByUsername(principal.getName());
		
		List<College> colleges = user.getColleges();
		
		List<Application> applicants_list =new ArrayList<Application>();
		
		List<User> us_list = new ArrayList<User>();
		
		for(College col : colleges)
		{
			List<Program> programs = col.getPrograms();
			for(Program prog : programs)
			{
				List<Application> applicants =applicationService.findByProgram(prog);
				applicants_list.addAll(applicants);
			}
		}
		
		for(Application app: applicants_list)
		{
			User us = app.getUser();
			if(!us_list.contains(us))
					us_list.add(us);
		}
		
		model.addAttribute("userlist",us_list);
		
		/*College college = collegeService.findByCollegename("Montgomery College");
		
		college.addUser(user);
		user.addCollege(college);
		userRepository.save(user);
		collegeRepository.save(college);*/
		
		return "viewallapplicants_sa";
			
	}
	
	 @RequestMapping("/blockfromuserlist")
	    public String blockfromuserlist(Principal principal, Model model, @Valid @ModelAttribute("user") User user, BindingResult result) {
		 	user.setActive("false");
		    userService.saveUser(user);
		    //System.out.println("in delete  " + user.getActive()+user.getUsername());
	    	String sc = viewapplicants(principal,model);
	    	return "viewallapplicants_sa";
	    }
}
