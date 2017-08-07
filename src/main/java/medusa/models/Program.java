package medusa.models;

import java.sql.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Program {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "startDate")
	private Date startDate;
	
	@Column(name = "endDate")
	private Date endDate;
	
	@Column(name = "active")
	private String active;
	
	@Column(name = "open")
	private String open;
	
	@Column(name = "deadline")
	private Date deadline;
	
	@Column(name = "classSize")
	private long classSize;
	
	@Column(name = "schedule")
	private String schedule;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "collegeId", nullable = false)
	private College college;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "programs")
	private List<User> users;
	
	@OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST})
    @JoinTable(joinColumns = @JoinColumn(name = "groupId"),inverseJoinColumns = @JoinColumn(name = "applicationId"))
	private List<Application> applications;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "programs")
	private List<Question> questions;
	
	public Program() {
		active = "true";
		open = "true";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public long getClassSize() {
		return classSize;
	}

	public void setClassSize(long classSize) {
		this.classSize = classSize;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public College getCollege() {
		return college;
	}

	public void setCollege(College college) {
		this.college = college;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Application> getApplications() {
		return applications;
	}

	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	
	public void addApplication(Application application) {
		applications.add(application);
	}
	
	public void removeApplication(Application application) {
		applications.remove(application);
	}
	
	public void addUser(User user) {
		users.add(user);
	}
	
	public void removeUser(User user) {
		users.remove(user);
	}
	
	public void addQuestion(Question question) {
		questions.add(question);
	}
	
	public void removeQuestion(Question question) {
		questions.remove(question);
	}
}
