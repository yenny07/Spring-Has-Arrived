package jpabook.yenyang.service;

import static org.assertj.core.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import jpabook.yenyang.domain.Address;
import jpabook.yenyang.domain.Book;
import jpabook.yenyang.domain.Item;
import jpabook.yenyang.domain.Member;
import jpabook.yenyang.domain.Order;
import jpabook.yenyang.domain.OrderStatus;
import jpabook.yenyang.exception.NotEnoughStockException;
import jpabook.yenyang.repository.OrderRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

	@PersistenceContext
	EntityManager em;

	@Autowired
	OrderService orderService;
	@Autowired
	OrderRepository orderRepository;

	Member member = Member.builder()
		.name("Rich.YeNyang")
		.address(new Address("안산", "겨울이네", "3648"))
		.build();
	Item item = new Book();

	@Test
	public void order() {
		// given
		em.persist(member);
		item = createBook("Toby's Spring", 5, 1000);
		int orderCount = 2;

		// when
		Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

		// then
		Order fromRepoOrder = orderRepository.findById(orderId)
			.orElseThrow(IllegalArgumentException::new);
		assertThat(fromRepoOrder.getId()).isEqualTo(orderId); // 주문 id
		assertThat(fromRepoOrder.getTotalPrice()).isEqualTo(2000); // 가격 * 개수 (1000 * 2)
		assertThat(fromRepoOrder.getStatus()).isEqualTo(OrderStatus.ORDER); // 주문 상태
		assertThat(item.getStockQuantity()).isEqualTo(3); // 주문량만큼 줄어든 재고
	}

	@Test(expected = NotEnoughStockException.class)
	public void 재고수량초과() throws Exception {
		//given
		em.persist(member);
		item = createBook("Toby's Spring", 5, 1000);
		int orderCount = 11;

		// when
		Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

		// then
		fail("누가 11개나 시켜요 없어요");
	}

	@Test
	public void cancelOrder() {
		//given
		em.persist(member);
		item = createBook("Toby's Spring", 5, 1000);
		int orderCount = 2;
		Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

		//when
		orderService.cancelOrder(orderId);

		//then
		Order order = orderRepository.findById(orderId)
			.orElseThrow(RuntimeException::new);
		assertThat(order.getStatus()).isEqualTo(OrderStatus.CANCEL);
		assertThat(item.getStockQuantity()).isEqualTo(5);

	}

	public Item createBook(String name, int quantity, int price) {
		Book book = new Book();
		book.setName(name);
		book.setStockQuantity(quantity);
		book.setPrice(price);
		em.persist(book);
		return book;
	}
}