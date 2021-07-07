package sp5.chap08.spring;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MemberRowMapper implements RowMapper<Member> {
	@Override
	public Member mapRow(ResultSet resultSet, int i) throws SQLException {
		Member member = new Member(
			resultSet.getString("EMAIL"),
			resultSet.getString("PASSWORD"),
			resultSet.getString("NAME"),
			resultSet.getTimestamp("REGDATE").toLocalDateTime()
		);
		member.setId(resultSet.getLong("ID"));
		return member;
	}
}
