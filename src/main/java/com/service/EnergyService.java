package com.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.entity.EnergyMeter;
import com.entity.ResultFormate;

public interface EnergyService {

	public EnergyMeter insertNewEnergy();

	public Map<LocalDateTime, Float> getHourlyConsumption();
	
	public List<EnergyMeter> getAllConsumption();
	
	public List<ResultFormate> getConsumption();
}