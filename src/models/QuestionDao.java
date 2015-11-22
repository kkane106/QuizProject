package models;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class QuestionDao {
	@PersistenceContext
	EntityManager em;
	
	public Question getQuestion(int id) {
		return em.find(Question.class, id);
	}
	
	public void updateQuestion(int id, String questionText, 
			List<String> answerTexts, int correctAnswer) {
		Question question = em.find(Question.class, id);
		question.setText(questionText);
		List<Answer> answers = question.getAnswers();
		for (int i = 0; i < answers.size(); i++) {
			Answer answer = answers.get(i);
			answer.setText(answerTexts.get(i));
			if (answer.getId() == correctAnswer) {
				answer.setCorrect(true);
			} else {
				answer.setCorrect(false);
			}
		}
		question.setAnswers(answers);
		em.persist(question);
	}

}
