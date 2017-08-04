package medusa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Threshold {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="active")
	private String active;
	
	@Column(name="level")
	private String level;
	
	
	@ManyToOne(fetch = FetchType.LAZY)	
	@JoinColumn(name = "programId", nullable = false)
	private Program program;
	
	@ManyToOne(fetch = FetchType.LAZY)	
	@JoinColumn(name = "questionId", nullable = false)
	private Question question;
}
