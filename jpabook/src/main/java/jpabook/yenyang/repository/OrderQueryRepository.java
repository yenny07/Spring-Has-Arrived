package jpabook.yenyang.repository;

import static jpabook.yenyang.domain.QMember.*;
import static jpabook.yenyang.domain.QOrder.*;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import jpabook.yenyang.domain.Order;
import jpabook.yenyang.domain.OrderSearch;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class OrderQueryRepository {
	private final JPAQueryFactory queryFactory;

	public List<Order> findAll(OrderSearch orderSearch) {
		return queryFactory.selectFrom(order)
			.innerJoin(order.member, member)
			.where(order.status.eq(orderSearch.getOrderStatus())
				.and(member.name.eq(orderSearch.getMemberName())))
			.fetch();
	}
}


