package medusa.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@RequestMapping(value="/createquestion", method = RequestMethod.POST)
    public String addQuestion(Model model, @Valid @ModelAttribute("program") Program program) {
    	model.addAttribute("question",new Question());
    	model.addAttribute("program", program);
    	return "addquestion";
    }
    
    @RequestMapping(value="/addquestion", method = RequestMethod.POST)
    public String addedQuestion(@Valid @ModelAttribute("question") Question question, @RequestParam("program") String name) {
    	Program prog = programService.findByName(name);
    	question.addProgram(prog);
    	prog.addQuestion(question);
    	questionService.saveQuestion(question);
    	programService.saveProgram(prog);
    	return "addedquestion";
    }
    
    @RequestMapping(value="/viewquestions", method = RequestMethod.POST)
    public String viewQuestions(@Valid @ModelAttribute("program") Program program, Model model) {
    	System.out.println(program);
    	System.out.println(programService.findByName(program.getName()).getQuestions());
    	List<Question> questions = programService.findByName(program.getName()).getQuestions();
    	model.addAttribute("questions", questions);
    	model.addAttribute("program", program);
    	return "viewquestions";
    }
	
}
