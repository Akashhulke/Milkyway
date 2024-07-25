package com.milkyway.service;

import java.util.List;

import com.milkyway.dto.CustomerDto;

public interface CustomerService {

	public String addNewCustomer(CustomerDto customerDto);
	
	public List<CustomerDto> getAllCustomers();
	
	public CustomerDto getCustomerById(long id);
	
	public List<String> getCustomerNames();
	
	public String deleteCustomerById(long id);

	public CustomerDto getCustomerByName(String name);
}
