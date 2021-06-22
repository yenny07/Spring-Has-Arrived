package jpabook.yenyang.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import jpabook.yenyang.domain.Item;

@Repository
public class ItemRepository {
	@PersistenceContext
	EntityManager em;

	public void save(Item item) {
		if (item.getId() == null) {
			em.persist(item);
		}
		else {
			em.merge(item); // 이미 한 번 영속화된 엔티티니 수정
		}
	}

	public Item findOne(Long id) {
		return em.find(Item.class, id);
	}

	public List<Item> findAll() {
		return em.createQuery("select i from Item i", Item.class)
			.getResultList();
	}

}
