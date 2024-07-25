package com.milkyway.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milkyway.entity.RateChart;
import com.milkyway.repository.RateChartRepository;

@Service
public class RateChartServiceImpl {
    @Autowired
    private RateChartRepository rateChartRepository;

    public RateChart getRateChart(double degree, double fat) {
        return rateChartRepository.findByDegreeAndFat(degree, fat);
    }
}
