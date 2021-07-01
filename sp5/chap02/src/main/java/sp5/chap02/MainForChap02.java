package sp5.chap02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainForChap02 {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationContext.class);

		Greeter g = context.getBean(Greeter.class);
		String msg = g.greet("스프링");
		System.out.println(msg);

		Greeter g2 = context.getBean(Greeter.class);
		System.out.println("(g == g2) = " + (g == g2)); // true

		context.close();
	}
}
