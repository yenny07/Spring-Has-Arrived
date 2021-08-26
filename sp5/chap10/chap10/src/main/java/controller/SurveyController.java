package controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import survey.Answer;
import survey.Question;

@Controller
@RequestMapping("/survey")
public class SurveyController {

	@GetMapping
	public ModelAndView form() {
		List<Question> questions = createQuestions();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("questions", questions); // Model
		modelAndView.setViewName("survey/surveyForm"); // View
		return modelAndView;
	}

	// 이걸 직접 쓰네.....
	private List<Question> createQuestions() {
		Question q1 = new Question("당신의 역할은 무엇입니까?", Arrays.asList("서버", "프론트", "풀스택"));
		Question q2 = new Question("많이 사용하는 개발도구는 무엇입니까?", Arrays.asList("Eclipse", "Intellij", "Sublime"));
		Question q3 = new Question("하고 싶은 말을 적어주세요.");

		return Arrays.asList(q1, q2, q3);
	}

	@PostMapping
	public String submit(@ModelAttribute("answerData") Answer answer) {
		return "survey/submitted";
	}
}
