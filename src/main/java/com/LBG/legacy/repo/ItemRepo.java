package com.LBG.legacy.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.LBG.legacy.domain.Item;

public interface ItemRepo extends JpaRepository<Item, Integer> {

}
