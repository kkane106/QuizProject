package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import models.Answer;
import models.Question;
import models.Quiz;
import models.QuizDao;

@Controller
@SessionAttributes({ "quiz" })
public class QuizController {

	@Autowired
	QuizDao quizDao;

	@ModelAttribute("quiz")
	public Quiz quiz() {
		return new Quiz();
	}

	// Index
	@RequestMapping(value = "/quizzes.do", method = RequestMethod.GET)
	public ModelAndView getQuizzes() {
		return new ModelAndView("quizzes", "quizzes", quizDao.getQuizzes());
	}

	// Show
	@RequestMapping(value = "/showQuiz.do", method = RequestMethod.GET)
	public ModelAndView getQuiz(@RequestParam("id") int id) {
		return new ModelAndView("quiz", "quiz", quizDao.getQuiz(id));
	}

	// New
	@RequestMapping(value = "/newQuiz.do", method = RequestMethod.GET)
	public ModelAndView newQueiz(@ModelAttribute("quiz") Quiz quiz, Model model) {
		model.addAttribute("quiz", new Quiz());
		return new ModelAndView("newQuiz");
	}

	// Create
	@RequestMapping(value = "/createQuiz.do", method = RequestMethod.POST)
	public ModelAndView createQuiz(@RequestParam(name = "quizName", required = false) String quizName,
			@RequestParam("question") String questionName, @RequestParam("answer") List<String> answers,
			@RequestParam("correct") int correct, @RequestParam(name = "continue", required = false) String cont,
			@RequestParam(name = "complete", required = false) String complete, @ModelAttribute("quiz") Quiz quiz,
			Model model) {

		if (quizName != null) {
			quiz.setName(quizName);
		}

		List<Question> questions = quiz.getQuestions();
		Question newQuestion = quizDao.newQuestion(questionName, answers, correct);

		if (questions == null) {
			questions = new ArrayList<>();
		}

		questions.add(newQuestion);
		quiz.setQuestions(questions);

		if (cont != null) {
			return new ModelAndView("newQuiz", "newQuiz", quiz);
		} else {
			return new ModelAndView("quiz", "quiz", quizDao.createQuiz(quiz));
		}
	}

	// Edit
	@RequestMapping(value = "/editQuiz.do", method = RequestMethod.GET)
	public ModelAndView editQuiz(@RequestParam("id") int id) {
		return new ModelAndView("editQuiz", "quiz", quizDao.getQuiz(id));
	}
	// Update
	@RequestMapping(value="updateQuiz.do", method=RequestMethod.POST)
	public ModelAndView updateQuiz(@RequestParam Map<String,String> requestParams, 
			@RequestParam("questions") List<String> newQuestions,
			@RequestParam("answers") List<String> newAnswers) {
		Quiz quiz = quizDao.getQuiz(Integer.parseInt(requestParams.get("quizId")));
		quiz.setName(requestParams.get("quizName"));
		List<Question> questions = updateQuestions(quiz.getQuestions(), newQuestions);
		List<String> correctAnswerIds = new ArrayList<String>(requestParams.keySet()).subList(4, requestParams.keySet().size());
		// Currently only configured for questions to have exaclty 4 answers
		for (int i=0 ; i < questions.size() ; i++) {
			List<String> questionAnswers = new ArrayList();
			for (int j=0; j < 4; j++) {
				questionAnswers.add(newAnswers.get(j));
			}
			questions.get(i).setAnswers(updateAnswers(questions.get(i), questionAnswers));
			newAnswers = newAnswers.subList(4, newAnswers.size());
			System.out.println(questions.get(i).getAnswers());
		}
		
		
		return new ModelAndView("quiz", "quiz", quiz);
	}

	public List<Question> updateQuestions(List<Question> questions, List<String> newQuestions) {
		for (int i = 0; i < questions.size(); i++) {
			if (questions.get(i).getText().equals(newQuestions.get(i))) {
			} else if (!questions.get(i).getText().equals(newQuestions.get(i))) {
				questions.get(i).setText(newQuestions.get(i));
			} else {
				// TODO!:: DEAL WITH ADDITIONAL QUESTIONS THAT ARE CREATED! /
				// DELETED
			}
		}
		return questions;
	}

	public List<Answer> updateAnswers(Question question, List<String> newAnswers) {
		List<Answer> answers = question.getAnswers();
		for (int i = 0; i < answers.size(); i++) {
			if (answers.get(i).getText().equals(newAnswers.get(i))) {

			} else if (!answers.get(i).getText().equals(newAnswers.get(i))) {
				answers.get(i).setText(newAnswers.get(i));
			} else {
				// TODO!:: DEAL WITH ADDITIONAL ANSWERS THAT ARE CREATED /
				// DELETED
			}
		}
		return answers;
	}
	// Delete
	@RequestMapping(value="deleteQuiz.do", method=RequestMethod.POST)
	public ModelAndView deleteQuiz(@RequestParam("id") int id) {
		quizDao.deleteQuiz(id);
		return new ModelAndView("quizzes", "quizzes", quizDao.getQuizzes());
	}
	// Score
	@RequestMapping(value="/evaluateQuiz.do", method=RequestMethod.POST)
	public ModelAndView evaluateQuiz(@RequestParam Map<String, String> requestParams) {
		double scores = quizDao.scoreQuiz(requestParams);
		return new ModelAndView("scores", "scores", scores);
	}
}
