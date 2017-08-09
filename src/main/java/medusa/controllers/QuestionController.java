package medusa.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import medusa.models.Program;
import medusa.models.Question;
import medusa.services.ProgramService;
import medusa.services.QuestionService;
import medusa.services.UserService;

@Controller
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private ProgramService programService;
	
	@RequestMapping(value="/addquestion", method = RequestMethod.GET)
    public String addQuestion(Model model) {
		System.out.println("helllllllo");
    	model.addAttribute("question",new Question());
    	return "addquestion";
    }
    
    @RequestMapping(value="/addquestion", method = RequestMethod.POST)
    public String addedQuestion(@Valid @ModelAttribute("question") Question question) {
    	Program prog = programService.findById(3);
    	System.out.println(prog);
    	System.out.println(question);
    	question.addProgram(prog);
    	prog.addQuestion(question);
    	questionService.saveQuestion(question);
    	programService.saveProgram(prog);
    	return "addedquestion";
    }
	
}
