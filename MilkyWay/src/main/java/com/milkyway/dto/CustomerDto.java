package com.milkyway.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

	private long customerId;
	private String customerName;
	private String customerVillage;
	private int customerPhoneno;
	private List<MilkCollectionDto> milkCollectionDto;
	private List<MoneyDto> moneyDetailsDto; 

}