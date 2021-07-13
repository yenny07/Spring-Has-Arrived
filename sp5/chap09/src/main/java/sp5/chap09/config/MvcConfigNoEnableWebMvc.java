package sp5.chap09.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class MvcConfigNoEnableWebMvc {
	@Bean
	public HandlerMapping handlerMapping() {
		RequestMappingHandlerMapping hm = new RequestMappingHandlerMapping();
		hm.setOrder(0);
		return hm;
	}

	@Bean
	public HandlerAdapter handlerAdapter() {
		return new RequestMappingHandlerAdapter();
	}

	@Bean
	public HandlerMapping simpleHandlerMapping() {
		SimpleUrlHandlerMapping simpleHM = new SimpleUrlHandlerMapping();
		Map<String, Object> pathMap = new HashMap<>();
		pathMap.put("/**", defaultServeltHandler());
		simpleHM.setUrlMap(pathMap);
		return simpleHM;
	}

	@Bean
	public HttpRequestHandler defaultServeltHandler() {
		return new DefaultServletHttpRequestHandler();
	}

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB_INF/view/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

}
