package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Answer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String text;
	
	@Column(name="isCorrect")
	private boolean correct;
	
	@ManyToOne
	@JoinColumn(name="QUESTION_ID")
	private Question question;

	public Answer(String text, boolean correct, Question question) {
		this.text = text;
		this.correct = correct;
		this.question = question;
	}
	
	public Answer() {}

	public String getText() {
		return text;
	}

	void setValue(String text) {
		this.text = text;
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	@Override
	public String toString() {
		return "Answer [text=" + text + ", correct=" + correct + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public void setText(String text) {
		this.text = text;
	}
}
