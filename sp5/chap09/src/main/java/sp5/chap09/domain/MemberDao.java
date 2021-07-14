package sp5.chap09.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

@Component
public class MemberDao {

	private JdbcTemplate jdbcTemplate;

	public MemberDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Member selectByEmail(String email) {
		List<Member> results = jdbcTemplate.query(
			"select * from MEMBER where EMAIL = ?",
			new MemberRowMapper(), email // Query에서 ?에 들어갈 파라미터
		);
		return results.isEmpty() ? null : results.get(0);
	}

	public void insert(Member member) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement pstmt = connection.prepareStatement(
					"insert into MEMBER(EMAIL, PASSWORD, NAME, REGDATE) values (?, ?, ?, ?)",
					new String[] {"ID"}); // 자동생성된 키 컬럼을 지정, 삽입한 Member 객체의 ID 값을 구할 수 있음
				pstmt.setString(1, member.getEmail());
				pstmt.setString(2, member.getPassword());
				pstmt.setString(3, member.getName());
				pstmt.setTimestamp(4, Timestamp.valueOf(member.getRegisterDateTime()));
				return pstmt;
			}
		}, keyHolder);
		Number keyValue = keyHolder.getKey();
		member.setId(keyValue.longValue());
	}

	public void update(Member member) {

		int changedRowCount = jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement pstmt = connection.prepareStatement(
					"update MEMBER set EMAIL=?, PASSWORD=?, NAME=?, REGDATE=? "
						+ "where ID = ?");
				pstmt.setString(1, member.getEmail());
				pstmt.setString(2, member.getPassword());
				pstmt.setString(3, member.getName());
				pstmt.setTimestamp(4, Timestamp.valueOf(member.getRegisterDateTime()));
				pstmt.setLong(5, member.getId());
				return pstmt;
			}
		});
	}

	public List<Member> selectAll() {
		List<Member> results = jdbcTemplate.query(
			"select * from MEMBER",
			new MemberRowMapper());
		return results;
	}

	public int count() {
		Integer count = jdbcTemplate.queryForObject( // 결과가 1행인 경우 사용
			"select count(*) from MEMBER", Integer.class);
		return count;
	}
}
