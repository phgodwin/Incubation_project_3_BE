package com.LBG.legacy.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.LBG.legacy.domain.Cart;
import com.LBG.legacy.repo.CartRepo;

@Service
public class CartService {

	private CartRepo repo;
//	private double totalPrice;

	public CartService(CartRepo repo) {
		super();
		this.repo = repo;
	}

	public ResponseEntity<Cart> createCart(Cart newCart) {
		Cart created = this.repo.save(newCart);
		return new ResponseEntity<Cart>(created, HttpStatus.CREATED);

	}

	public List<Cart> getCarts() {
		return this.repo.findAll();
	}

	public ResponseEntity<Cart> getCart(int id) {
		Optional<Cart> found = this.repo.findById(id);

		if (found.isEmpty()) {
			return new ResponseEntity<Cart>(HttpStatus.NOT_FOUND);
		}

		Cart body = found.get();

		return ResponseEntity.ok(body);

	}

	public ResponseEntity<Cart> updateCart(int id, Cart newCart) {
		Optional<Cart> found = this.repo.findById(id);

		if (found.isEmpty()) {
			return new ResponseEntity<Cart>(HttpStatus.NOT_FOUND);
		}

		Cart existing = found.get();

		if (newCart.getCustomer() != null) {
			existing.setCustomer(newCart.getCustomer());
		}

//		if (newCart.getId() != null) {
//			existing.setId(newCart.getId());
//		}
//
////		if (newItem.getPrice() != null) {
////			existing.setPrice(newItem.getPrice());
////		}
//
//		if (newCart.getItem() != null) {
//			existing.setItem(newCart.getItem());
//		}

		Cart updated = this.repo.save(existing);

		return ResponseEntity.ok(updated);
	}

	public boolean remove(int id) {
		this.repo.deleteById(id);

		return !this.repo.existsById(id);
	}

//	public void totalPrice() {
//		totalPrice = 0;
//		for (int i = 0; i < item.length(); i++) {
//			totalPrice + list[i];
//		}
//
//	}

}
