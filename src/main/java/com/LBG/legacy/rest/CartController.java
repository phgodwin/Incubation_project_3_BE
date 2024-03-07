package com.LBG.legacy.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.LBG.legacy.domain.Cart;
import com.LBG.legacy.services.CartService;

@RestController
@CrossOrigin
@RequestMapping("/cart")

public class CartController {
	private CartService service;

	public CartController(CartService service) {
		super();
		this.service = service;
	}


	@PostMapping("/create")
	public ResponseEntity<Cart> create(@RequestBody Cart newCart) {
		return this.service.createCart(newCart);
	}

	@GetMapping("/get")
	public List<Cart> getCarts() {
		return this.service.getCarts();
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Cart> getCart(@PathVariable int id) {
		return this.service.getCart(id);
	}

	@PatchMapping("/update/{id}")
	public ResponseEntity<Cart> updateCart(@PathVariable int id, @RequestBody Cart newCart) {
		return this.service.updateCart(id, newCart);
	}

	@DeleteMapping("/remove/{id}")
	public boolean remove(@PathVariable int id) {
		return this.service.remove(id);
	}

	@DeleteMapping("/removeAll")
	public ResponseEntity<String> removeAllItems() {
		boolean removedAll = this.service.removeAll();

		if (removedAll) {
			return new ResponseEntity<>("All carts have been removed.", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Failed to remove all carts.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
