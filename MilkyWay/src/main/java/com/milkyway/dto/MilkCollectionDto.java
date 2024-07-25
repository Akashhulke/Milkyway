package com.milkyway.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MilkCollectionDto {

	private Long id;
	private double quantity;
	private double fat;
	private double temperature;
	private double rate;
	private double totalAmount;
	private String timeOfDay;
	private Date date = new Date();;
}