package com.milkyway.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoneyDto {
	
	private Long moneyId;
	private double paidAmount;
	private double balanceAmount; 
	private Date date = new Date();
	private String note; 
}
