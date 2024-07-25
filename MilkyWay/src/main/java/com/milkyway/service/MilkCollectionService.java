package com.milkyway.service;

import java.util.Date;
import java.util.List;

import com.milkyway.dto.MilkCollectionDto;

public interface MilkCollectionService {
	
	public String addNewCollectionByCxId(Long customerId, MilkCollectionDto mcDto);
	
	public String deleteMilkCollectionById(Long cxId, Long mcId);
	
	public List<MilkCollectionDto> getMilkCollectionByDate(Date date);
	
	public List<MilkCollectionDto> getMilkCollectionByCxIdAndMcDate(Long cxId, String date);
	
}
