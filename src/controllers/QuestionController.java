package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import models.Question;
import models.QuestionDao;
import models.QuizDao;

@Controller
public class QuestionController {
	@Autowired
	QuestionDao questionDao;
	
	@Autowired
	QuizDao quizDao;
	
//	Edit
	@RequestMapping(value="/editQuestion.do", method=RequestMethod.GET)
	public ModelAndView editQuestion(@RequestParam("id") int id,
			@RequestParam("quizId") int quizId) {
		ModelAndView mv = new ModelAndView("editQuestion", "question", questionDao.getQuestion(id));
		mv.addObject("quizId", quizId);
		return mv;
	}
	
//	Update
	@RequestMapping(value="/updateQuestion.do", method=RequestMethod.POST)
	public ModelAndView updateQuestion(@RequestParam("questionId") int id,
			@RequestParam("quizId") int quizId,
			@RequestParam("question") String question,
			@RequestParam("answers") List<String> answers,
			@RequestParam("correct") int correctAnswer) {
		questionDao.updateQuestion(id, question, answers, correctAnswer);
		
		return new ModelAndView("editQuiz", "quiz", quizDao.getQuiz(quizId));
	}
	
//	New
	@RequestMapping(value="/newQuestion.do", method=RequestMethod.GET)
	public ModelAndView newQuestion(@RequestParam("quizId") int quizId) {
		return new ModelAndView("newQuestion", "quiz", quizDao.getQuiz(quizId));
	}
	
//	Create
	@RequestMapping(value="/createQuestion.do", method=RequestMethod.POST)
	public ModelAndView createQuestion(@RequestParam("quizId") int quizId,
			@RequestParam("question") String questionText,
			@RequestParam("answers") List<String> answers,
			@RequestParam("correct") int correctAnswerId) {
		quizDao.addQuestion(quizId, questionText, answers, correctAnswerId);
		return new ModelAndView("editQuiz", "quiz", quizDao.getQuiz(quizId));
	}
}
