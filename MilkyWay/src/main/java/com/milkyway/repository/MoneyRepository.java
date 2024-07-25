package com.milkyway.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.milkyway.entity.Money;

public interface MoneyRepository extends JpaRepository<Money, Long>{

}
