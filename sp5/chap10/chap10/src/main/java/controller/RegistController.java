package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import spring.DuplicateMemberException;
import spring.MemberRegisterService;
import spring.RegisterRequest;

@Controller
@RequestMapping("/register")
public class RegistController {
	private MemberRegisterService memberRegisterService;

	public void setMemberRegisterService(
		MemberRegisterService memberRegisterService) {
		this.memberRegisterService = memberRegisterService;
	}

	@RequestMapping("/step1")
	public String handleStep1() {
		return "register/step1";
	}

	@PostMapping("/step2")
	public String handleStep2(
		@RequestParam(value = "agree", defaultValue = "false") Boolean agree,
		RegisterRequest request) {
		if (!agree) {
			return "register/step1";
		}
		return "register/step2";
	}

	@GetMapping("/step2")
	public String handleStep2Get() {
		return "redirect:/register/step1";
	}

	@PostMapping("/step3")
	public String handleStep3(@ModelAttribute("registerRequest") RegisterRequest regReq, Errors errors) {
		new RegisterRequestValidator().validate(regReq, errors);
		if (errors.hasErrors()) { // validate()에서 나온 에러가 있다면~ 돌아가라~
			return "register/step2";
		}
		try {
			memberRegisterService.regist(regReq);
			return "register/step3";
		} catch (DuplicateMemberException ex) {
			errors.rejectValue("email", "duplicate");
			return "register/step2";
		}
	}
}
