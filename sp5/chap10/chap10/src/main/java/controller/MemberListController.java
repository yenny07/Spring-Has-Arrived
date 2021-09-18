package controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.Member;
import spring.MemberDao;

@Controller
public class MemberListController {

	private MemberDao memberDao;

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	@RequestMapping("/members")
	public String list(
		@ModelAttribute("cmd") ListCommand listCommand,
		Errors errors,
		Model model
	) {
		// @DateTimeFormat에 지정한 형식에 맞지 않으면 Errors 객체에 "typeMismatch" 에러코드 추가
		if (errors.hasErrors()) {
			return "member/memberList";
		}
		if (listCommand.getFrom() != null && listCommand.getTo() != null) {
			List<Member> members = memberDao.selectByRegdate(
				listCommand.getFrom(), listCommand.getTo()
			);
			model.addAttribute("members", members);
		}
		return "member/memberList";
	}
}
