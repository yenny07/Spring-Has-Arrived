package controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.AuthInfo;
import spring.ChangePasswordService;
import spring.WrongIdPasswordException;

@Controller
@RequestMapping("/edit/changePassword")
public class ChangePwdController {
	private ChangePasswordService changePasswordService;

	public void setChangePasswordService(ChangePasswordService changePasswordService) {
		this.changePasswordService = changePasswordService;
	}

	@GetMapping
	public String form(
		@ModelAttribute("command") ChangePwdCommand pwdCommand,
		HttpSession httpSession) {
		AuthInfo authInfo = (AuthInfo)httpSession.getAttribute("authInfo");
		if (authInfo == null) { // 로그인 없이 비밀번호를 변경하려는 경우, 로그인 화면으로 리다이렉트
			return "redirect:/login";
		}
		return "edit/changePwdForm";
	}

	@PostMapping
	public String submit(
		@ModelAttribute("command") ChangePwdCommand pwdCommand,
		Errors errors,
		HttpSession httpSession) {

		new ChangePwdCommandValidator().validate(pwdCommand, errors);

		if (errors.hasErrors()) {
			return "edit/changePwdForm";
		}
		// 서버 재시작 시 httpSession은 만료 -> null을 리턴
		AuthInfo authInfo = (AuthInfo)httpSession.getAttribute("authInfo");

		try {
			changePasswordService.changePassword(
				authInfo.getEmail(),
				pwdCommand.getCurrentPassword(),
				pwdCommand.getNewPassword());
			return "edit/changedPwd";
		} catch (WrongIdPasswordException e) {
			errors.rejectValue("currentPassword", "notMatching");
			return "edit/changePwdForm";
		}
	}
}
