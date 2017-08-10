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
import medusa.models.Response;
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
    public String addedQuestion(@Valid @ModelAttribute("question") Question question, @RequestParam("program") long id) {
    	Program prog = programService.findById(id);
    	question.addProgram(prog);
    	prog.addQuestion(question);
    	questionService.saveQuestion(question);
    	programService.saveProgram(prog);
    	return "addedquestion";
    }
    
    @RequestMapping(value="/viewquestions", method = RequestMethod.POST)
    public String viewQuestions(@Valid @ModelAttribute("program") Program program, Model model) {
    	List<Question> questions = programService.findByName(program.getName()).getQuestions();
    	model.addAttribute("questions", questions);
    	model.addAttribute("program", program);
    	return "viewquestions";
    }
    
    @RequestMapping(value="/viewuniversalquestions", method = RequestMethod.GET)
    public String viewUniversalQuestions(Model model) {
    	Program program = programService.findByName("universal");
    	List<Question> questions = program.getQuestions();
    	model.addAttribute("questions", questions);
    	model.addAttribute("program", program);
    	return "viewquestions";
    }
    
    @RequestMapping(value="/viewresponses", method = RequestMethod.POST)
    public String viewResponses(Model model, @Valid @ModelAttribute("question") Question question) {
    	Question q = questionService.findByContent(question.getContent());
    	List<Response> responses = q.getResponses();
    	model.addAttribute("question", q);
    	model.addAttribute("responses", responses);
    	return "viewresponses";
    }
    
    @RequestMapping(value="removequestion", method = RequestMethod.POST)
    public String deleteQuestion(Model model, @Valid @ModelAttribute("question") Question question, @RequestParam("programID") long id) {
    	Question q = questionService.findByContent(question.getContent());
    	q.setActive("false");
    	questionService.saveQuestion(q);
    	model.addAttribute("program",programService.findById(id));
    	model.addAttribute("questions", programService.findById(id).getQuestions());
    	return "redirect:/viewquestions";
    }
    
    @RequestMapping(value="editquestion", method = RequestMethod.POST)
    public String editQuestion(Model model, @Valid @ModelAttribute("question") Question question, @RequestParam("programID") long id) {
    	model.addAttribute("program",programService.findById(id));
    	model.addAttribute("question", question);
    	return "editquestion";
    }
	
}
