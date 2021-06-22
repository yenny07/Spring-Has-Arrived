package jpabook.yenyang.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import jpabook.yenyang.repository.MemberRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class MemberServiceTest {

	@Autowired
	MemberService memberService;

	@Autowired
	MemberRepository memberRepository;

	@Test
	public void join() {
		// 귀참ㅎ다. 매우.
	}
}