package com.milkyway.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milkyway.dto.CustomerDto;
import com.milkyway.dto.MilkCollectionDto;
import com.milkyway.entity.Customer;
import com.milkyway.entity.MilkCollection;
import com.milkyway.mapper.CustomerMapper;
import com.milkyway.repository.CustomerRepository;
import com.milkyway.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	/*
	 * This service method is created to store customer details without
	 * MilkCollection details.
	 */
	@Override
	public String addNewCustomer(CustomerDto cxDto) {
		
		customerRepository.save(CustomerMapper.INSTANCE.toEntity(cxDto));		
		return "Registered Successfully";
	}

	@Override
	public List<CustomerDto> getAllCustomers() {
		List<CustomerDto> customerDtoList = new ArrayList<CustomerDto>();
		
		for(Customer newCustomer: customerRepository.findAll()) {
			customerDtoList.add(CustomerMapper.INSTANCE.toDto(newCustomer));
		}
		return customerDtoList;
	}

	@Override
	public CustomerDto getCustomerByName(String name) {

		CustomerDto newDto = new CustomerDto();

		try {
			Customer customer = customerRepository.findByCustomerName(name);

			newDto.setCustomerId(customer.getCustomerId());
			newDto.setCustomerName(customer.getCustomerName());
			newDto.setCustomerPhoneno(customer.getCustomerPhoneno());
			newDto.setCustomerVillage(customer.getCustomerVillage());

			List<MilkCollectionDto> listDto = new ArrayList<MilkCollectionDto>();

			for (MilkCollection newCollection : customer.getMilkCollection()) {

				MilkCollectionDto newMilk = new MilkCollectionDto();

				newMilk.setId(newCollection.getId());
				newMilk.setDate(newCollection.getDate());
				newMilk.setTimeOfDay(newCollection.getTimeOfDay());
				newMilk.setFat(newCollection.getFat());
				newMilk.setTemperature(newCollection.getTemperature());
				newMilk.setQuantity(newCollection.getQuantity());
				newMilk.setRate(newCollection.getRate());
				newMilk.setTotalAmount(newCollection.getTotalAmount());

				listDto.add(newMilk);
			}
			newDto.setMilkCollectionDto(listDto);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return newDto;
	}

	@Override
	public List<String> getCustomerNames() {

		return customerRepository.getDistictCustomerNames();
	}

	@Override
	public String deleteCustomerById(long id) {
		customerRepository.deleteById(id);
		return "Deleted";
	}

	@Override
	public CustomerDto getCustomerById(long id) {
		return CustomerMapper.INSTANCE.toDto(customerRepository.findById(id).get());
	}
}
