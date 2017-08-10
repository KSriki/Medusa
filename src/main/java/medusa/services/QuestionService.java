package medusa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import medusa.models.Question;
import medusa.repositories.QuestionRepository;

@Service
public class QuestionService {
	@Autowired
    QuestionRepository questionRepository;
    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }
    
    public void saveQuestion(Question question) {
    	questionRepository.save(question);
    }
    
    public Question findByContent(String content) {
    	return questionRepository.findByContent(content);
    }
    
    public Question findByContentAndActive(String content, String active) {
    	return questionRepository.findByContentAndActive(content, active);
    }
    
    public Question findById(long id) {
    	return questionRepository.findById(id);
    }
}
