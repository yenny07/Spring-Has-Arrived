package sp5.chap02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainForChap06 {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationContext.class);

		Client client = context.getBean(Client.class);
		client.send();

		Client2 client2 = context.getBean(Client2.class);
		client2.send();

		context.close();
	}
}

