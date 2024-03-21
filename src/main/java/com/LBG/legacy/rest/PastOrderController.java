package com.LBG.legacy.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.LBG.legacy.domain.PastOrder;
import com.LBG.legacy.services.PastOrderService;

@RestController
@CrossOrigin
@RequestMapping("/pastorder")
public class PastOrderController {

	private PastOrderService service;

	public PastOrderController(PastOrderService service) {
		super();
		this.service = service;
	}

	@PostMapping("/create")
	public ResponseEntity<PastOrder> createPastOrders(@RequestBody PastOrder newPastOrders) {
		return this.service.createPastOrder(newPastOrders);
	}

	@GetMapping("/get")
	public List<PastOrder> getPastOrder() {
		return this.service.getPastOrders();
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<PastOrder> getPastOrder(@PathVariable int id) {
		return this.service.getPastOrder(id);
	}

}