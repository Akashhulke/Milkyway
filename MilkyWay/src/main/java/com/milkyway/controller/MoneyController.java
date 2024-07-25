package com.milkyway.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.milkyway.dto.MoneyDto;
import com.milkyway.serviceimpl.MoneyServiceImpl;

@RestController
@RequestMapping("/api/money")
@CrossOrigin("http://localhost:4200/")
public class MoneyController {
	
	@Autowired
	private MoneyServiceImpl moneyServiceImpl; 
	
	@PostMapping("/{cxId}")
	public ResponseEntity<String> addMoneyDetails(@PathVariable Long cxId, @RequestBody MoneyDto moneyDto){		
		String money = null; 
		try {
			money = moneyServiceImpl.addMoneyByCxId(cxId, moneyDto);
			return ResponseEntity.status(HttpStatus.CREATED).body(money);
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/{cxId}")
	public ResponseEntity<List<MoneyDto>> getMoneyDetailsByCxId(@PathVariable Long cxId){
		List<MoneyDto> list = moneyServiceImpl.getMoneyDetails(cxId);
		if (list.size() <= 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} else {
			return ResponseEntity.of(Optional.of(list));
		}
	}
}
