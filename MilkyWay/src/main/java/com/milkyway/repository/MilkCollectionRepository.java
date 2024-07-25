package com.milkyway.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.milkyway.entity.MilkCollection;

@Repository
public interface MilkCollectionRepository extends JpaRepository<MilkCollection, Long>{
	
	public List<MilkCollection> findByDate(Date date);
}
