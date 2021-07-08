package sp5.chap08.main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import sp5.chap08.config.AppContext;
import sp5.chap08.spring.ChangePasswordService;
import sp5.chap08.spring.Member;
import sp5.chap08.spring.MemberDao;
import sp5.chap08.spring.MemberNotFoundException;
import sp5.chap08.spring.WrongIdPasswordException;

public class MainForMemberDao {
	private static MemberDao memberDao;

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppContext.class);
		memberDao = context.getBean(MemberDao.class);

		// insertFirstMember();

		selectAll();
		updateMember();
		insertMember();

		context.close();
	}

	private static void insertFirstMember() {
		System.out.println("----firstMember");

		Member member = new Member("yenny07@naver.com", "1234", "yein", LocalDateTime.now());
		memberDao.insert(member);
		System.out.println(member.getId() + "의 아이디로 첫 멤버 추가");
	}

	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddHHmmss");

	private static void insertMember() {
		System.out.println("-----insertMember");

		String prefix = formatter.format(LocalDateTime.now());
		Member member = new Member(prefix + "@test.com", prefix, prefix, LocalDateTime.now());
		memberDao.insert(member);
		System.out.println(member.getId() + "추가");
	}

	private static void updateMember() {
		System.out.println("-----updateMember");
		Member member = memberDao.selectByEmail("yenny07@naver.com");
		String oldPassword = member.getPassword();
		String newPassword = Double.toHexString(Math.random());
		member.changePassword(oldPassword, newPassword);

		memberDao.update(member);
		System.out.println("암호변경 :" + oldPassword + "->" + newPassword);
	}

	private static void selectAll() {
		System.out.println("-----selectAll");
		int total = memberDao.count();
		System.out.println("전체 데이터 개수: " + total);
		List<Member> members = memberDao.selectAll();
		for (Member m : members) {
			System.out.println(m.getId() + m.getEmail() + m.getName());
		}
	}
}
