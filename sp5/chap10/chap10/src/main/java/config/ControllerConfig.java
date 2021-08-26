package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import controller.HelloController;
import controller.RegistController;
import controller.SurveyController;
import spring.MemberRegisterService;

@Configuration
public class ControllerConfig {
	// 9장, 10장 ONLY
	@Bean
	public HelloController helloController() {
		return new HelloController();
	}

	@Autowired
	private MemberRegisterService memberRegisterService;

	@Bean
	public RegistController registController() {
		RegistController controller = new RegistController();
		controller.setMemberRegisterService(memberRegisterService);
		return controller;
	}

	@Bean
	public SurveyController surveyController() {
		return new SurveyController();
	}
}