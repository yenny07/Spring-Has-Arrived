package interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class AuthCheckInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(
		HttpServletRequest request,
		HttpServletResponse response,
		Object handler) throws Exception {
		HttpSession session = request.getSession(false);

		// 세선에 authInfo가 존재하면(로그인 상태라면) true 리턴
		if (session != null) {
			Object authInfo = session.getAttribute("authInfo");
			if (authInfo != null) {
				return true;
			}
		}
		// 없다면 "컨텍스트 경로 + /login"으로 리다이렉트를 생성한 뒤 false 리턴
		response.sendRedirect(request.getContextPath() + "/login");
		return false;
	}
}
