package com.LBG.legacy.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.LBG.legacy.domain.Cart;

public interface CartRepo extends JpaRepository<Cart, Integer> {

}
