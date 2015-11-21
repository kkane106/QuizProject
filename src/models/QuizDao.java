package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class QuizDao {

	@PersistenceContext
	EntityManager em;

	public List<Quiz> getQuizzes() {
		return (List<Quiz>) em.createQuery("SELECT q FROM Quiz q").getResultList();
	}

	public Quiz getQuiz(int id) {
		return (Quiz) em.find(Quiz.class, id);
	}

	public double scoreQuiz(Map<String, String> requestParams) {
		List<String> questionIds = new ArrayList<String>(requestParams.keySet());
		questionIds.remove(0);
		List<Question> questions = em.find(Quiz.class, Integer.parseInt(requestParams.get("quiz"))).getQuestions();
		int counter = 0;
		for (int i = 0; i < questionIds.size(); i++) {
			if (Integer.parseInt(requestParams.get(questionIds.get(i))) == questions.get(i).getCorrectAnswer()
					.getId()) {
				++counter;
			}
		}
		return ((double) counter / questions.size()) * 100;
	}

	public Quiz createQuiz(Quiz quiz) {
		em.persist(quiz);
		return quiz;
	}

	public Question newQuestion(String questionText, List<String> answers, int correct) {
		Question question = new Question();
		question.setText(questionText);
		
		List<Answer> answersList = new ArrayList<>();
		for (String text : answers) {
			Answer a = new Answer(text, false, question);
			answersList.add(a);
		}
		answersList.get(correct).setCorrect(true);
		question.setAnswers(answersList);
		return question;
	}
}
