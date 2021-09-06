package controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.AuthInfo;
import spring.AuthService;
import spring.WrongIdPasswordException;

@Controller
@RequestMapping("/login")
public class LoginController {
	private AuthService authService;

	public void setAuthService(AuthService authService) {
		this.authService = authService;
	}

	@GetMapping
	public String form(LoginCommand loginCommand) {
		return "login/loginForm";
	}

	@PostMapping
	public String submit(@ModelAttribute("loginCommand") LoginCommand loginCommand, Errors errors, HttpSession httpSession) {
		new LoginCommandValidator().validate(loginCommand, errors);
		if (errors.hasErrors()) {
			return "login/loginForm";
		}
		try {
			// 인증된 정보 객체를 httpSession에 추가
			AuthInfo authInfo = authService.authenticate(
				loginCommand.getEmail(),
				loginCommand.getPassword());
			httpSession.setAttribute("authInfo", authInfo);

			return "login/loginSuccess";
		} catch (WrongIdPasswordException e) {
			errors.reject("idPasswordNotMatching");
			return "login/loginForm";
		}
	}
}
