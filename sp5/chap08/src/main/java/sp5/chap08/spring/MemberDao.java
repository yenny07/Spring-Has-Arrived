package sp5.chap08.spring;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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
			new RowMapper<Member>() {
				@Override
				public Member mapRow(ResultSet resultSet, int rowNum) throws SQLException {
					Member member = new Member(
						resultSet.getString("EMAIL"),
						resultSet.getString("PASSWORD"),
						resultSet.getString("NAME"),
						resultSet.getTimestamp("REGDATE").toLocalDateTime()
					);
					member.setId(resultSet.getLong("ID"));
					return member;
				}
			}, email // Query에서 ?에 들어갈 파라미터
		);
		return results.isEmpty() ? null : results.get(0);
	}

	public void insert(Member member) {

	}

	public void update(Member member) {

	}

	public List<Member> selectAll() {
		List<Member> results = jdbcTemplate.query("select * from MEMBER",
			(ResultSet rs, int rowNum) -> {
				Member member = new Member(
					rs.getString("EMAIL"),
					rs.getString("PASSWORD"),
					rs.getString("NAME"),
					rs.getTimestamp("REGDATE").toLocalDateTime());
				member.setId(rs.getLong("ID"));
				return member;
			});
		return results;
	}
}
