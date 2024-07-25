package com.milkyway.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milkyway.dto.MoneyDto;
import com.milkyway.entity.Customer;
import com.milkyway.entity.Money;
import com.milkyway.repository.CustomerRepository;
import com.milkyway.repository.MoneyRepository;
import com.milkyway.service.MoneyService;


@Service
public class MoneyServiceImpl implements MoneyService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private MoneyRepository moneyRepository;

	@Override
	public String addMoneyByCxId(Long cxId, MoneyDto moneyDto) {
System.out.println("in the money service. ");
		Customer cx = customerRepository.getById(cxId); // find the required customer from DB.
		List<Money> list = cx.getMoneyDetails(); // get the money details list for the required customer.

		Money md = new Money(); // make a new money object to be saved into DB
		md.setMoneyId(moneyDto.getMoneyId());
		md.setDate(moneyDto.getDate());
		md.setPaidAmount(moneyDto.getPaidAmount());
		md.setBalanceAmount(moneyDto.getBalanceAmount());
		md.setNote(moneyDto.getNote());

		list.add(md); // add it to the list.
		cx.setMoneyDetails(list);
		customerRepository.save(cx); // save into DB.
		return "Added Success";
	}

	@Override
	public List<MoneyDto> getMoneyDetails(Long cxId) {
		List<MoneyDto> dtoList = new ArrayList<>();
		Customer cx = customerRepository.getById(cxId);
		List<Money> moneyList = cx.getMoneyDetails();
		for (Money my : moneyList) {
			MoneyDto moneyDto = new MoneyDto();
			moneyDto.setMoneyId(my.getMoneyId());
			moneyDto.setDate(my.getDate());
			moneyDto.setPaidAmount(my.getPaidAmount());
			moneyDto.setBalanceAmount(my.getBalanceAmount());
			moneyDto.setNote(my.getNote());
			dtoList.add(moneyDto);
		}
		return dtoList;
	}
}

