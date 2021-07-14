package sp5.chap09.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import sp5.chap09.controller.HelloController;
import sp5.chap09.controller.RegisterController;
import sp5.chap09.service.MemberRegisterService;

// @Configuration
public class ControllerConfig {
	@Autowired
	private MemberRegisterService memberRegSvc;

	@Bean
	public RegisterController registerController() {
		RegisterController controller = new RegisterController();
		// controller.setMemberRegisterService(memberRegSvc);
		return controller;
	}

	@Bean
	public HelloController helloController() {
		return new HelloController();
	}
}
