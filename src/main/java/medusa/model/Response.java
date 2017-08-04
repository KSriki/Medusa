package medusa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;

@Entity
public class Response {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name="content")
	private String content;

	@Column(name="active")
	private String active;
	
	@ManyToOne(fetch = FetchType.LAZY)	
	@JoinColumn(name = "questionId", nullable = false)
	private Question question;
	
	@ManyToOne(fetch = FetchType.LAZY)	
	@JoinColumn(name = "applicationId", nullable = false)
	private Application application;
	
}
