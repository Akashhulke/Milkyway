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
public class MilkCollection {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "quantity")
	private double quantity;

	@Column(name = "fat")
	private double fat;

	@Column(name = "temperature")
	private double temperature;

	@Column(name = "rate")
	private double rate;

	@Column(name = "total_amount")
	private double totalAmount;

	@Column(name = "time_of_day")
	private String timeOfDay;

	@Column(columnDefinition = "DATE")
	@Temporal(TemporalType.DATE)
	private Date date = new Date();
//	
//	@ManyToOne
////	@JsonBackReference
//	private Customer Customer;
}

