package com.milkyway.serviceimpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milkyway.dto.DateComparatorDto;
import com.milkyway.dto.MilkCollectionDto;
import com.milkyway.entity.Customer;
import com.milkyway.entity.MilkCollection;
import com.milkyway.entity.RateChart;
import com.milkyway.repository.CustomerRepository;
import com.milkyway.repository.MilkCollectionRepository;
import com.milkyway.service.MilkCollectionService;

@Service
public class MilkCollectionServiceImpl implements MilkCollectionService{
	
	@Autowired
	MilkCollectionRepository milkCollectionRepository;
	
	@Autowired
	CustomerRepository customerRepository; 
	
	@Autowired
	RateChartServiceImpl rateChartServiceImpl;
	
	/* This API is used to add milk collection details of existing customer. */
	@Override
	public String addNewCollectionByCxId(Long customerId, MilkCollectionDto dto) {
		
		Customer cx = customerRepository.getById(customerId);  // find the customer of given Id. 
		
		List<MilkCollection> newlist = cx.getMilkCollection(); // create a new list of milkCollection 
		
		MilkCollection newMilk = new MilkCollection();         // new MilkCollection object. 
		newMilk.setId(dto.getId());
		newMilk.setDate(dto.getDate());
		newMilk.setFat(dto.getFat());
		newMilk.setQuantity(dto.getQuantity());
		newMilk.setTemperature(dto.getTemperature());
		newMilk.setTimeOfDay(dto.getTimeOfDay());              // set all the properties to milkCollection object. 
		
		RateChart rateChart = rateChartServiceImpl.getRateChart(dto.getTemperature(),dto.getFat()); // calculate the rate and amount. 
		dto.setRate(rateChart.getRate());
		dto.setTotalAmount(dto.getQuantity() * dto.getRate());
		newMilk.setRate(dto.getRate());
		newMilk.setTotalAmount(dto.getTotalAmount());
		
		newlist.add(newMilk); 									// add the milk collection details to the MilkCollection list.
		cx.setMilkCollection(newlist); 							// set the list to the customer. 
		customerRepository.save(cx);
		
		return "updated Successfully";
	}

	/* Need to delete the milk collection for the particular customer */
	@Override
	public String deleteMilkCollectionById(Long cxId, Long mcId) {
		Customer cx = customerRepository.getById(cxId);					// find the customer by customerID
		List<MilkCollection> mclist = cx.getMilkCollection();			// get the milk collection list for that particular cx
		for(int i = 0; i<mclist.size(); i++) {							// loop through the list and remove the collection from the list
			if(mclist.get(i).getId() == mcId) {
				mclist.remove(i);
			}
		}
		cx.setMilkCollection(mclist);									// set that particular list back to the same customer and save. 
		customerRepository.save(cx);
		return "Deleted Milk Collection Sucessfully";
	}
	
	/* This API is used to pull milk collection data on the basis of date. */

	@Override
	public List<MilkCollectionDto> getMilkCollectionByDate(Date date) {
		List<MilkCollectionDto> mcDto = new ArrayList<>();
		List<MilkCollection> mclist = milkCollectionRepository.findByDate(date); // get all the milk collection details based on date
		
		for(MilkCollection mc: mclist) {										// loop through the list and set it to the DTO object. 
			MilkCollectionDto mcObj = new MilkCollectionDto(); 
			mcObj.setId(mc.getId());
			mcObj.setDate(mc.getDate());
			mcObj.setTimeOfDay(mc.getTimeOfDay());
			mcObj.setFat(mc.getFat());
			mcObj.setTemperature(mc.getTemperature());
			mcObj.setQuantity(mc.getQuantity());
			mcObj.setRate(mc.getRate());
			mcObj.setTotalAmount(mc.getTotalAmount());
			mcDto.add(mcObj);												 // add it to the dto list and return the list 
		}
		return mcDto;
	}

	/*
	 * This method will find the customer based on id and then list out the milk
	 * collection details based on date given.
	 */
	@Override
	public List<MilkCollectionDto> getMilkCollectionByCxIdAndMcDate(Long cxId, String date) {
		
		List<MilkCollectionDto> mcDtolist = new ArrayList<>(); 			// this dto list to be returned upon request
		 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  	// this is to format the incoming String date to date format
		Date date1 = null; 
		try {
			 date1 = sdf.parse(date);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
		Customer cx = customerRepository.getById(cxId);
		List<MilkCollection> mcList = cx.getMilkCollection();	
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(date1);
		calendar2.add(Calendar.DAY_OF_YEAR, 0); 					// incrementing date by one day until it reaches 10 days. 
		Date newDate2 = calendar2.getTime();
		
		for(MilkCollection mc: mcList) {
			
			if(mc.getDate().compareTo(newDate2) >=0 ) {
				MilkCollectionDto mcDto = new MilkCollectionDto();
				mcDto.setId(mc.getId());
				mcDto.setDate(mc.getDate());
				mcDto.setTimeOfDay(mc.getTimeOfDay());
				mcDto.setFat(mc.getFat());
				mcDto.setTemperature(mc.getTemperature());
				mcDto.setQuantity(mc.getQuantity());
				mcDto.setRate(mc.getRate());
				mcDto.setTotalAmount(mc.getTotalAmount());
				mcDtolist.add(mcDto);
			}	
		}									 
		Collections.sort(mcDtolist,new DateComparatorDto());
		return mcDtolist;
	}
}
