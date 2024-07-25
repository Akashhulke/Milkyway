package com.milkyway.service;

import java.util.List;

import com.milkyway.dto.MoneyDto;

public interface MoneyService {

	public String addMoneyByCxId(Long cxId, MoneyDto moneyDto);
	
	public List<MoneyDto> getMoneyDetails(Long cxId);
}
