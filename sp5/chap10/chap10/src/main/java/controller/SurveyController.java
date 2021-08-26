package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import survey.Answer;

@Controller
@RequestMapping("/survey")
public class SurveyController {

	@GetMapping
	public String form() {
		return "survey/surveyForm";
	}

	@PostMapping
	public String submit(@ModelAttribute("answerData") Answer answer) {
		return "survey/submitted";
	}
}
