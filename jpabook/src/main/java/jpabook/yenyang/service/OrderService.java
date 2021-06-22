package jpabook.yenyang.service;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.stereotype.Service;

import jpabook.yenyang.domain.Delivery;
import jpabook.yenyang.domain.Item;
import jpabook.yenyang.domain.Member;
import jpabook.yenyang.domain.Order;
import jpabook.yenyang.domain.OrderItem;
import jpabook.yenyang.repository.MemberRepository;
import jpabook.yenyang.repository.OrderRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrderService {
	private final MemberRepository memberRepository;
	private final OrderRepository orderRepository;
	private final ItemService itemService;

	public Long order(Long memberId, Long itemId, int count) {
		Member member = memberRepository.findOne(memberId);
		Item item = itemService.findOne(itemId);

		Delivery delivery = new Delivery(member.getAddress());
		OrderItem orderItem = OrderItem.builder()
			.item(item)
			.orderPrice(item.getPrice())
			.count(count).build();
		Order order = Order.builder()
			.member(member)
			.orderItems(Collections.singletonList(orderItem))
			.delivery(delivery)
			.build();

		orderRepository.save(order);
		return order.getId();
	}

	public void cancelOrder(Long orderId) {
		Order order = orderRepository.findById(orderId)
			.orElseThrow(RuntimeException::new);
		order.cancel();
	}
}
