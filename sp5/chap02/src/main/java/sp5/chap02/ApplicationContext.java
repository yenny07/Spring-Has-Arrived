package sp5.chap02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ApplicationContext {

	@Bean
	public Greeter greeter() {
		Greeter g = new Greeter();
		g.setFormat("%s, 메롱 메롱");
		return g;
	}

	@Bean
	public Client client() {
		Client client = new Client();
		client.setHost("호스트");
		return client;
	}

	@Bean(initMethod = "connect", destroyMethod = "close")
	public Client2 client2() {
		Client2 client = new Client2();
		client.setHost("호스트");
		return client;
	}
}
