package controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CookieValue;
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
	public String form(
		LoginCommand loginCommand,
		@CookieValue(value = "REMEMBER", required = false) Cookie cookie) {

		// REMEMBER라는 이름의 쿠키가 존재하면, 커맨드 객체의 email 프로퍼티 값 설정
		if (cookie != null) {
			loginCommand.setEmail(cookie.getValue());
			loginCommand.setRememberEmail(true);
		}

		return "login/loginForm";
	}

	@PostMapping
	public String submit(
		@ModelAttribute("loginCommand") LoginCommand loginCommand,
		Errors errors,
		HttpSession httpSession,
		HttpServletResponse response) { // 쿠키 생성을 위해
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

			// 쿠키 생성과 삭제
			Cookie rememberCookie = new Cookie("REMEMBER", loginCommand.getEmail());
			rememberCookie.setPath("/");
			if (loginCommand.isRememberEmail()) {
				rememberCookie.setMaxAge(60 * 60 * 24 * 30);
			} else {
				rememberCookie.setMaxAge(0);
			}
			response.addCookie(rememberCookie);

			return "login/loginSuccess";
		} catch (WrongIdPasswordException e) {
			errors.reject("idPasswordNotMatching");
			return "login/loginForm";
		}
	}
}
