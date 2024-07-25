package com.milkyway.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.milkyway.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	@Query("SELECT DISTINCT customerName FROM Customer")
	public List<String> getDistictCustomerNames();
	
	public Customer findByCustomerName(String customerName);
}

