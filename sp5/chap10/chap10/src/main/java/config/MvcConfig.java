package config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import controller.RegisterRequestValidator;

@Configuration
@EnableWebMvc // OptionalValidatorFactoryBean을 global scope Validator로 등록
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/view/", ".jsp");
	}

	// 컨트롤러 없이 경로 매핑
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/main").setViewName("main"); // /main 요청경로에 대해 뷰 이름으로 main(.jsp)를 사용한다
	}

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasenames("message.label_ko"); //message 패키지 속 label 프로퍼티 파일로부터 메세지를 읽어오렴!
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	// global scope Validator를 여기서 설정해두면 OptionalValidatorFactoryBean을 global scope validator로 쓰지 않으니 주석 처리.
	// @Override
	// public Validator getValidator() {
	// 	return new RegisterRequestValidator();
	// }
}
