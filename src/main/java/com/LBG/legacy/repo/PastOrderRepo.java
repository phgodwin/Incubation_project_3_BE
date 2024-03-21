package com.LBG.legacy.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.LBG.legacy.domain.PastOrder;

public interface PastOrderRepo extends JpaRepository<PastOrder, Integer> {

}