package medusa.controllers;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginRegistrationController {
	 
	@RequestMapping("/login")
    public String login(){
        return "login";
    }
	
	
} 

  //  @RequestMapping("/userprofile")
   // public String userprofile(Principal principal,Model model){
    //	User user = userService.findByUsername(principal.getName());
    	//System.out.println(user.getUsername());
    //	model.addAttribute("user", user);
      //  return "userprofile";
//}
