package medusa.repositories;

import org.springframework.data.repository.CrudRepository;

import medusa.models.Question;

public interface QuestionRepository extends CrudRepository<Question, Long> {

	public Question findByContent(String content);
	public Question findByContentAndActive(String content, String active);
	public Question findById(long id);
}
