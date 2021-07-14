package sp5.chap09;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"sp5.chap09.*"})
@SpringBootApplication
public class Chap09Application {

	public static void main(String[] args) {
		SpringApplication.run(Chap09Application.class, args);
	}

}
