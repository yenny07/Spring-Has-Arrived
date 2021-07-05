package sp5.chap08.config;

// Tomcat JDBC 모듈이 제공. javax.sql.DataSource를 구현한 클래스. 커넥션 풀을 제공.
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBConfig {

	@Bean(destroyMethod = "close") // close()는 커넥션 풀에 보관된 Connection 을 닫는다.
	public DataSource dataSource() {
		DataSource ds = new DataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver"); // JDBC 드라이버 클래스 지정
		ds.setUrl("jdbc:mysql://localhost/spring5fs?characterEncoding=utf8");
		ds.setUsername("spring5");
		ds.setPassword("spring5");
		ds.setInitialSize(2); 	// 커넥션 풀 초기화 시 생성할 초기 커넥션 수
		ds.setMaxActive(10);	// 커넥션 풀에서 가져올 수 있는 최대 커넥션 개수수
		ds.setTestWhileIdle(true);	// 유휴 커넥션 검사
		ds.setMinEvictableIdleTimeMillis(1000 * 60 * 3);	// 최소 유휴 시간 3분
		ds.setTimeBetweenEvictionRunsMillis(1000 * 10); // 10초 주기
		return ds;
	}
}
