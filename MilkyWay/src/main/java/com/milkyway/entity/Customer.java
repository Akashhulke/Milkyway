package com.milkyway.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long customerId;
	
	@Column(name = "CUSTOMER_NAME")
	private String customerName;
	
	@Column(name = "VILLAGE")
	private String customerVillage;
	
	@Column(name = "PHONE_NO")
	private int customerPhoneno;

	@OneToMany(cascade = CascadeType.ALL)
	private List<MilkCollection> milkCollection;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Money> moneyDetails; 

}
