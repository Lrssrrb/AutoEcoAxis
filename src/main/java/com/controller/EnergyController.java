package com.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.EnergyMeter;
import com.entity.ResultFormate;
import com.service.EnergyService;

@RestController
public class EnergyController {
	
	@Autowired
	EnergyService energyService;
	
	@Scheduled(cron = "0 * * * * *")
    public void runEveryMinuteTask() {
		energyService.insertNewEnergy();
	}
		
	@GetMapping("hourly")
	public Map<LocalDateTime, Float> showHourly() {
		Map<LocalDateTime, Float> hourlyConsumption = energyService.getHourlyConsumption();
		return hourlyConsumption;
	}
	
	@GetMapping("")
	public List<EnergyMeter> showAll() {
		List<EnergyMeter> hourlyConsumption = energyService.getAllConsumption();
		return hourlyConsumption;
	}
	
	@GetMapping("result")
	public List<ResultFormate> showAllData() {
		List<ResultFormate> hourlyConsumption = energyService.getConsumption();
		return hourlyConsumption;
	}

}
