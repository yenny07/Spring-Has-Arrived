package jpabook.yenyang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jpabook.yenyang.domain.Item;
import jpabook.yenyang.repository.ItemRepository;

@Service
public class ItemService {

	@Autowired
	ItemRepository itemRepository;

	public Long save(Item item) {
		itemRepository.save(item);
		return item.getId();
	}

	public Item findOne(Long itemId) {
		return itemRepository.findOne(itemId);
	}

	public List<Item> findAll() {
		return itemRepository.findAll();
	}
}
