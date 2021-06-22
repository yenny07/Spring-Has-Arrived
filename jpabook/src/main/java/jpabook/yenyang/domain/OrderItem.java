package jpabook.yenyang.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.omg.PortableInterceptor.ObjectReferenceFactory;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ORDER_ITEM_ID")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ITEM_ID")
	private Item item;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORDER_ID")
	private Order order;

	private int orderPrice;
	private int count;

	@Builder
	public OrderItem(Item item, int orderPrice, int count) {
		this.item = item;
		this.orderPrice = orderPrice;
		this.count = count;

		item.removeStock(count);
	}

	public void cancel() {
		getItem().addStock(count);
	}

	public int getTotalPrice() {
		return getOrderPrice() * getCount();
	}
}
