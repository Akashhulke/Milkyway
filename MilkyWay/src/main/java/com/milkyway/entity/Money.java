package com.milkyway.entity;

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Money {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long moneyId; 
	
	@Column(name = "PAID_AMT")
	private double paidAmount;
	
	@Column(name = "BAL_AMT")
	private double balanceAmount; 
	
	@Column(name = "NOTE")
	private String note; 
	
	@Column(columnDefinition = "DATE")
	@Temporal(TemporalType.DATE)
	private Date date = new Date();
}
