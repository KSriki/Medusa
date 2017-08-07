package medusa.models;

import java.util.ArrayList;
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
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    
    @Column(name = "password")
    private String password;
    
    @Column(name = "firstName")
    private String firstName;
    
    @Column(name = "lastName")
    private String lastName;
    
    @Column(name = "username")
    private String username;
    
    @Column(name = "role")
    private String role;
    
    @Column(name = "active")
    private String active;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(joinColumns = @JoinColumn(name = "userId"),inverseJoinColumns = @JoinColumn(name = "collegeId"))
    private List<College> colleges;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(joinColumns = @JoinColumn(name = "userId"),inverseJoinColumns = @JoinColumn(name = "programId"))
    private List<Program> programs;
    
    @OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST})
    @JoinTable(joinColumns = @JoinColumn(name = "userId"),inverseJoinColumns = @JoinColumn(name = "applicationId"))
    private List<Application> applications;
    
    public User() {
    	this.active = "true";
    	this.colleges = new ArrayList<College>();
    	this.programs = new ArrayList<Program>();
    	this.applications = new ArrayList<Application>();
    }
    public User(String email, String password, String firstName, String lastName, String username, String role) {
    	this.email = email;
    	this.password = password;
    	this.firstName = firstName;
    	this.lastName = lastName;
    	this.username = username;
    	this.role = role;
    	this.active = "true";
    	this.colleges = new ArrayList<College>();
    	this.programs = new ArrayList<Program>();
    	this.applications = new ArrayList<Application>();
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public List<College> getColleges() {
		return colleges;
	}

	public void setColleges(List<College> colleges) {
		this.colleges = colleges;
	}

	public List<Program> getPrograms() {
		return programs;
	}

	public void setPrograms(List<Program> programs) {
		this.programs = programs;
	}

	public List<Application> getApplications() {
		return applications;
	}

	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}
	
	public void addCollege(College college) {
		colleges.add(college);
	}
	
	public void removeCollege(College college) {
		colleges.remove(college);
	}
	
	public void addProgram(Program program) {
		programs.add(program);
	}
	
	public void removeProgram(Program program) {
		programs.remove(program);
	}
	
	public void addApplication(Application application) {
		applications.add(application);
	}
	
	public void removeApplication(Application application) {
		applications.remove(application);
	}
}
