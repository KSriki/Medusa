package medusa.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



import medusa.models.Application;

@Entity
public class Response {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
	public Response() {
		active = "true";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}
	
}
