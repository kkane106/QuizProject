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
@SessionAttributes({"quiz"})
public class QuizController {
	
	@Autowired
	QuizDao quizDao;
	
	@ModelAttribute("quiz")
	public Quiz quiz() {
		return new Quiz();
	}
//	Index
	@RequestMapping(value="/quizzes.do", method=RequestMethod.GET)
	public ModelAndView getQuizzes() {
		List<Quiz> quizzes = quizDao.getQuizzes(); 
		return new ModelAndView("quizzes", "quizzes", quizzes);
	}
//	Show
	@RequestMapping(value="/showQuiz.do", method=RequestMethod.GET)
	public ModelAndView getQuiz(@RequestParam("id") int id) {
		return new ModelAndView("quiz", "quiz", quizDao.getQuiz(id));
	}
//	New
	@RequestMapping(value="/newQuiz.do", method=RequestMethod.GET)
	public ModelAndView newQueiz(@ModelAttribute("quiz") Quiz quiz, Model model) {
		model.addAttribute("quiz", new Quiz());
		return new ModelAndView("newQuiz");
	}
//	Create
	@RequestMapping(value="/createQuiz.do", method=RequestMethod.POST)
	public ModelAndView createQuiz(@RequestParam(name="quizName", required=false) String quizName, 
			@RequestParam("question") String questionName, @RequestParam("answer") List<String> answers,
			@RequestParam("correct") int correct,
			@RequestParam(name="continue", required=false) String cont, 
			@RequestParam(name="complete", required=false) String complete,
			@ModelAttribute("quiz") Quiz quiz, Model model) {
		
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
//	Edit
//	Update
//	Delete
//	Score
	@RequestMapping(value="/evaluateQuiz.do", method=RequestMethod.POST)
	public ModelAndView evaluateQuiz(@RequestParam Map<String,String> allRequestParams) {
		double scores = quizDao.scoreQuiz(allRequestParams);
		return new ModelAndView("scores", "scores", scores);
	}

}
