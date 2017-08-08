package medusa.controllers;

import java.io.UnsupportedEncodingException;
import java.security.Principal;

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
import medusa.models.User;
import medusa.services.UserService;
import medusa.validators.UserValidator;

@Controller
public class HomeController {
	
	@Autowired
	private UserValidator userValidator;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	public EmailService emailService;
	
	
	
	/*
	 * Change to dynamically choose which dashboard depending on sec-auth
	 * 
	 * 
	 * 
	 */
	
	@RequestMapping("/")
	   public String index(Model model){
	        return "homepage";
	  }
    
    @RequestMapping(value="/register", method = RequestMethod.GET)
    public String showRegistrationPage(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }
    @RequestMapping(value="/register", method = RequestMethod.POST)
    public String processRegistrationPage(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) throws UnsupportedEncodingException{
        model.addAttribute("user", user);
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            return "registration";
        } else {
            userService.saveUser(user);
            model.addAttribute("message", "User Account Successfully Created");
            sendEmailWithoutTemplating_reg(user.getUsername(),user.getEmail());
        }
        return "userprofile";
    }
    
    @RequestMapping("/userprofile")
    public String userprofile(Principal principal,Model model){
    	User user = userService.findByUsername(principal.getName());
    	//System.out.println(user.getUsername());
    	model.addAttribute("user", user);
        return "userprofile";
    }
    
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    
    
    /*
     * Change for sec auth, so it doesnt show up if user is already logged in
     * 
     * 
     */
//    @RequestMapping(value="/login", method = RequestMethod.POST)
//    public String successfulLogin(Model model){
//    	
//        return "dashboard";
//    }
    
    public void sendEmailWithoutTemplating_reg(String username,String useremail) throws UnsupportedEncodingException{

		  final Email email = DefaultEmail.builder()

		        .from(new InternetAddress("samazon.infosys@gmail.com", "Medusa Admin"))

		        .to(Lists.newArrayList(new InternetAddress(useremail, username)))

		        .subject("Your Registration in Medusa")

		        .body("Hello "+username+", Thanks for Registering with Medusa Educaion Platform.")

		        .encoding("UTF-8").build();

		  emailService.send(email);

		}
}
