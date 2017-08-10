package medusa.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;

import medusa.models.Program;
import medusa.models.User;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class College {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name="active")
	private String active;

	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH})
	@JoinTable(joinColumns = @JoinColumn(name = "collegeId"),inverseJoinColumns = @JoinColumn(name = "programId"))
	private List<Program> programs;

	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(joinColumns = @JoinColumn(name = "collegeId"),inverseJoinColumns = @JoinColumn(name = "userId"))
	private List<User> users;

	public College() {
		this.active = "true";
		this.programs = new ArrayList<Program>();
		this.users = new ArrayList<User>();
	}
	
	public College(String name) {
		this.name = name;
		this.active = "true";
		this.programs = new ArrayList<Program>();
		this.users = new ArrayList<User>();
	}
	
	public void addProgram(Program p) {
		programs.add(p);
	}
	
	public void removeProgram(Program p) {
		programs.remove(p);
	}
	
	public void addUser(User u) {
		users.add(u);
	}
	
	public void removeUser(User u) {
		users.remove(u);
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

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public List<Program> getPrograms() {
		return programs;
	}

	public void setPrograms(List<Program> programs) {
		this.programs = programs;
	}

	public List<User> getusers() {
		return users;
	}

	public void setusers(List<User> users) {
		this.users = users;
	}
	

}
