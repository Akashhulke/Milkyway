package com.milkyway.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.milkyway.dto.MilkCollectionDto;
import com.milkyway.serviceimpl.MilkCollectionServiceImpl;

@RestController
@RequestMapping("/api/milk-collection")
@CrossOrigin("http://localhost:4200/")
public class MilkCollectionController {

	@Autowired
	MilkCollectionServiceImpl milkCollectionServiceImpl;

	/*
	 * This API takes customer id and milkCollectionDto information and stores the
	 * new milk collection information into the DB
	 */
	@PostMapping("/{id}")
	public ResponseEntity<String> addNewCustomer(@PathVariable Long id, @RequestBody MilkCollectionDto mcDto) {
		String cDto = null;
		try {
			cDto = milkCollectionServiceImpl.addNewCollectionByCxId(id, mcDto);
			return ResponseEntity.status(HttpStatus.CREATED).body(cDto);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/* This API will delete a MilkCollection by customer Id and milkCollection id */
	@DeleteMapping("/{cxId}/{mcId}")
	public ResponseEntity<String> deleteACustomerById(@PathVariable Long cxId, @PathVariable Long mcId) {
		String cDto = null;
		try {
			cDto = milkCollectionServiceImpl.deleteMilkCollectionById(cxId, mcId);
			return ResponseEntity.status(HttpStatus.CREATED).body(cDto);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	/* This API is used to find all the milk collections based on the date given. */
	@GetMapping("/{date}")
	public ResponseEntity<List<MilkCollectionDto>> getMilkCollectionByDate(
			@PathVariable(name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
		List<MilkCollectionDto> list = milkCollectionServiceImpl.getMilkCollectionByDate(date);
		if (list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} else {
			return ResponseEntity.of(Optional.of(list));
		}
	}
	
	@GetMapping("/{cxId}/{date}")
	public ResponseEntity<List<MilkCollectionDto>> getMilkCollectionByDateForCxId( @PathVariable(name = "cxId") Long cxId,
			@PathVariable(name = "date") String date) {
		List<MilkCollectionDto> list = milkCollectionServiceImpl.getMilkCollectionByCxIdAndMcDate(cxId, date);
		if (list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} else {
			return ResponseEntity.of(Optional.of(list));
		}
	}
}

