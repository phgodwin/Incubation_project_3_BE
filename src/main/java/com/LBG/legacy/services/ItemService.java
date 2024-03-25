package com.LBG.legacy.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.LBG.legacy.domain.Item;
import com.LBG.legacy.repo.ItemRepo;

@CrossOrigin("*")
@Service
public class ItemService {
	private ItemRepo repo;

	public ItemService(ItemRepo repo) {
		super();
		this.repo = repo;
	}

	public ResponseEntity<Item> createItem(Item newItem) {
		Item created = this.repo.save(newItem);
		return new ResponseEntity<Item>(created, HttpStatus.CREATED);

	}

	public List<Item> getItems() {
		return this.repo.findAll();
	}

//	public ResponseEntity<Item> getItem(int id) {
//		Optional<Item> found = this.repo.findById(id);
//
//		if (found.isEmpty()) {
//			return new ResponseEntity<Item>(HttpStatus.NOT_FOUND);
//		}
//
//		Item body = found.get();
//
//		return ResponseEntity.ok(body);
//
//	}

	public ResponseEntity<Item> updateItem(int id, Item newItem) {
		Optional<Item> found = this.repo.findById(id);

		if (found.isEmpty()) {
			return new ResponseEntity<Item>(HttpStatus.NOT_FOUND);
		}

		Item existing = found.get();

		if (newItem.getName() != null) {
			existing.setName(newItem.getName());
		}

		if (newItem.getPrice() != null && existing.getPrice() != null) {
			double epsilon = 0.0001;
			if (Math.abs(existing.getPrice().doubleValue() - newItem.getPrice().doubleValue()) > epsilon) {
				existing.setPrice(newItem.getPrice());
			}
		}

		if (newItem.getQuantity() != null && newItem.getQuantity() != 0) {

			existing.setQuantity(newItem.getQuantity());

		}

		if (newItem.getCart() != null) {
			existing.setCart(newItem.getCart());
		}

		Item updated = this.repo.save(existing);

		return ResponseEntity.ok(updated);
	}

	public ResponseEntity<Item> removeCartItem(int id) {

		Optional<Item> found = this.repo.findById(id);

		if (found.isEmpty()) {
			return new ResponseEntity<Item>(HttpStatus.NOT_FOUND);
		}

		Item existing = found.get();

		existing.setCart(null);

		Item updated = this.repo.save(existing);

		return ResponseEntity.ok(updated);
	}

	public boolean remove(int id) {
		this.repo.deleteById(id);

		return !this.repo.existsById(id);
	}

}
