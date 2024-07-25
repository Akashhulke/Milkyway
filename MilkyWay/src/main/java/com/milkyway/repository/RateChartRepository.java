package com.milkyway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.milkyway.entity.RateChart;

@Repository
public interface RateChartRepository extends JpaRepository<RateChart, Long> {
	
    public RateChart findByDegreeAndFat(double degree, double fat);
}
