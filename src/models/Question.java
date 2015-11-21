package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Question {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String text;
	
	@ManyToMany(mappedBy="questions")
	private List<Quiz> quizzes;
	
	@OneToMany(mappedBy="question", cascade=CascadeType.ALL)
	private List<Answer> answers;
	
	@Transient
	private String givenAnswer;

	public Question(int id, String text, List<Answer> answers) {
		this.id = id;
		this.text = text;
		this.answers = answers;
	}
	
	public Question() {}

	public int getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public String getGivenAnswer() {
		return givenAnswer;
	}

	public void setGivenAnswer(String givenAnswer) {
		this.givenAnswer = givenAnswer;
	}

	public Answer getCorrectAnswer() {
		for (Answer answer : answers) {
			if (answer.isCorrect())
				return answer;
		}
		return null;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", text=" + text + ", answers="
				+ answers + ", givenAnswer=" + givenAnswer + "]";
	}

	public List<Quiz> getQuizzes() {
		return quizzes;
	}

	public void setQuizzes(List<Quiz> quizzes) {
		this.quizzes = quizzes;
	}

	public void setId(int id) {
		this.id = id;
	}
}
