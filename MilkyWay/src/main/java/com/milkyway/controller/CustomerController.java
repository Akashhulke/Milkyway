package com.milkyway.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.milkyway.dto.CustomerDto;
import com.milkyway.serviceimpl.CustomerServiceImpl;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin("http://localhost:4200/")
public class CustomerController {

	@Autowired
	private CustomerServiceImpl customerServiceImpl;

	/*
	 * This API returns all the customer lists with their related milkCollection
	 * details.
	 */
	@GetMapping
	public ResponseEntity<List<CustomerDto>> getCustomers() {
		List<CustomerDto> list = customerServiceImpl.getAllCustomers();
		if (list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} else {
			return ResponseEntity.of(Optional.of(list));
		}
	}

	/* This API is use to add new customer details. */
	@PostMapping
	public ResponseEntity<String> addNewCustomer(@RequestBody CustomerDto customerDto) {
		String cDto = null;
		try {
			cDto = customerServiceImpl.addNewCustomer(customerDto);
			return ResponseEntity.status(HttpStatus.CREATED).body(cDto);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}
	}

	/* This API will delete a customer by ID */
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteACustomerById(@PathVariable Long id) {
		String cDto = null;
		try {
			cDto = customerServiceImpl.deleteCustomerById(id);
			return ResponseEntity.status(HttpStatus.CREATED).body(cDto);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/names")
	public ResponseEntity<List<String>> getAllCustomerNames() {

		List<String> list = customerServiceImpl.getCustomerNames();
		if (list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} else {
			return ResponseEntity.of(Optional.of(list));
		}
	}

	/*
	 * This API is used to find the customer by ID. If the given customer doesn't
	 * exist, it'll return NOT_FOUND status.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<CustomerDto> findById(@PathVariable Long id) {
		CustomerDto customerDto = customerServiceImpl.getCustomerById(id);

		if (customerDto != null) {
			return ResponseEntity.of(Optional.of(customerDto));
		} else {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}

