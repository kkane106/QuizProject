package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import models.AnswerDao;

@Controller
public class AnswerController {
	@Autowired
	AnswerDao answerDao;

}
