package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="QUIZ")
public class Quiz {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	
	@Transient
	private int numberOfQuestions;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="QUIZ_QUESTION",
		joinColumns = @JoinColumn(name = "QUIZ_ID") , 
		inverseJoinColumns = @JoinColumn(name = "QUESTION_ID") )
	private List<Question> questions;

	public String getName() {
		return name;
	}

	public void setName(String n) {
		this.name = n;
	}

	public int getNumberOfQuestions() {
		return numberOfQuestions;
	}

	public void setNumberOfQuestions(int i) {
		this.numberOfQuestions = i;
	}

	public List<Question> getQuestions() {
		return questions;
	}
	
	public void setQuestions(List<Question> q) {
		this.questions = q;
	}

	public int getId() {
		return id;
	}

	public void setId(int i) {
		this.id = i;
	}
}
