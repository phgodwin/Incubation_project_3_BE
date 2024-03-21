package com.LBG.legacy.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.LBG.legacy.domain.PastOrder;
import com.LBG.legacy.repo.PastOrderRepo;

@CrossOrigin("*")
@Service
public class PastOrderService {
	private PastOrderRepo repo;

	public PastOrderService(PastOrderRepo repo) {
		super();
		this.repo = repo;
	}

	public ResponseEntity<PastOrder> createPastOrder(PastOrder newPastOrder) {
		PastOrder created = this.repo.save(newPastOrder);
		return new ResponseEntity<PastOrder>(created, HttpStatus.CREATED);

	}

	public List<PastOrder> getPastOrders() {
		return this.repo.findAll();
	}

	public ResponseEntity<PastOrder> getPastOrder(int id) {
		Optional<PastOrder> found = this.repo.findById(id);

		if (found.isEmpty()) {
			return new ResponseEntity<PastOrder>(HttpStatus.NOT_FOUND);
		}

		PastOrder body = found.get();

		return ResponseEntity.ok(body);

	}

}