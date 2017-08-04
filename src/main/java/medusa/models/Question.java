package medusa.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "content")
	private String content;
	
	@Column(name = "active")
	private String active;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "choices")
	private String choices;
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(name = "questionId"),inverseJoinColumns = @JoinColumn(name = "programId"))
	private List<Program> programs;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
	@JoinTable(joinColumns = @JoinColumn(name = "questionId"),inverseJoinColumns = @JoinColumn(name = "responseId"))
	private List<Response> responses;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
	@JoinTable(joinColumns = @JoinColumn(name = "questionId"),inverseJoinColumns = @JoinColumn(name = "thresholdId"))
	private List<Threshold> thresholds;
	
	public Question() {
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getChoices() {
		return choices;
	}

	public void setChoices(String choices) {
		this.choices = choices;
	}

	public List<Program> getPrograms() {
		return programs;
	}

	public void setPrograms(List<Program> programs) {
		this.programs = programs;
	}

	public List<Response> getResponses() {
		return responses;
	}

	public void setResponses(List<Response> responses) {
		this.responses = responses;
	}

	public List<Threshold> getThresholds() {
		return thresholds;
	}

	public void setThresholds(List<Threshold> thresholds) {
		this.thresholds = thresholds;
	}
	
	public void addProgram(Program program) {
		programs.add(program);
	}
	
	public void removeProgram(Program program) {
		programs.remove(program);
	}
	
	public void addResponse(Response response) {
		responses.add(response);
	}
	
	public void removeResponse(Response response) {
		programs.remove(response);
	}
	
	public void addThreshold(Threshold threshold) {
		thresholds.add(threshold);
	}
	
	public void removeThreshold(Threshold threshold) {
		thresholds.remove(threshold);
	}
}
