package medusa.models;

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
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name="active")
	private String active;

	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
	@JoinTable(joinColumns = @JoinColumn(name = "collegeId"),inverseJoinColumns = @JoinColumn(name = "programId"))
	private List<Program> programs;

	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
	private List<User> siteAdmins;

	
	public void addProgram(Program p) {
		programs.add(p);
	}
	
	public void removeProgram(Program p) {
		programs.remove(p);
	}
	
	public void addUser(User u) {
		siteAdmins.add(u);
	}
	
	public void removeUser(User u) {
		siteAdmins.remove(u);
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

	public List<User> getSiteAdmins() {
		return siteAdmins;
	}

	public void setSiteAdmins(List<User> siteAdmins) {
		this.siteAdmins = siteAdmins;
	}

}
