package medusa.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import medusa.models.Program;
import medusa.services.ProgramService;
import medusa.services.UserService;
import medusa.models.User;

@Controller
public class SysAdminCreateController {

	@Autowired
	private UserService userService;
	@Autowired
	private ProgramService programService;
	
	@RequestMapping(value="createprogram", method = RequestMethod.GET)
	public String createProgram(Model model, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		model.addAttribute("program", new Program());
		model.addAttribute("padmins", userService.findByRoleAndActive("PADMIN", "true"));
		model.addAttribute("college", user.getColleges().get(0));
		return "createprogram";
	}
}
