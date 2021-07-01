package sp5.chap02;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Client implements InitializingBean, DisposableBean {
	private String host;

	public void setHost(String host) {
		this.host = host;
	}

	public void send() {
		System.out.println("Client.send() to " + host);
	}

	// 소멸 : 컨테이너 종료 시 호줄됨 (prototype 일 경우 호출되지 않음)
	@Override
	public void destroy() throws Exception {
		System.out.println("Client.destroy() 실행");
	}

	// 초기화 : 빈 객체 생성 직후 호출됨
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("Client.afterPropertiesSet() 실행");
	}
}
