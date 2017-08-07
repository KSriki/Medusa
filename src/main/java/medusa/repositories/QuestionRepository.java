package medusa.repositories;

import org.springframework.data.repository.CrudRepository;

import medusa.models.Question;

public interface QuestionRepository extends CrudRepository<Question, Long> {

}
