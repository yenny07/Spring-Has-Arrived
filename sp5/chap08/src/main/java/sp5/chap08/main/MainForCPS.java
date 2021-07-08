package sp5.chap08.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import sp5.chap08.config.AppContext;
import sp5.chap08.spring.ChangePasswordService;
import sp5.chap08.spring.MemberNotFoundException;
import sp5.chap08.spring.WrongIdPasswordException;

public class MainForCPS {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppContext.class);

		ChangePasswordService passwordService = context.getBean(ChangePasswordService.class);

		try {
			passwordService.changePassword("yenny07@naver.com", "5678", "1234");
			System.out.println("암호 변경 완료~~~");
		} catch (
			MemberNotFoundException e) {
			System.out.println("회원 데이터가 존재하지 않아용");
		} catch (
			WrongIdPasswordException e) {
			System.out.println("암호가 올바르지 않아용");
		}
	}
}
