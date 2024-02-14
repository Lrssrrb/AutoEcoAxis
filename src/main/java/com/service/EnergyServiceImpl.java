package com.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.EnergyMeter;
import com.entity.ResultFormate;
import com.repository.EnergyRepository;

import jakarta.annotation.PostConstruct;

@Service
public class EnergyServiceImpl implements EnergyService{
	
	static int value = 0;
	
	@Autowired
    private EnergyRepository energyMeterRepository;
	
	@PostConstruct
	private void insertEnergyMeterValues() {
		
        LocalDateTime currentTimestamp = LocalDateTime.now();
        LocalDateTime startTimestamp = currentTimestamp.minusHours(2);

        Random random = new Random();

        for (int i = 0; i <= 120; i++) {
            LocalDateTime timestamp = startTimestamp.plusMinutes(i);
            value+=(random.nextFloat() * 10);
            float minuteValue = value;
            
            if(timestamp.getMinute() == 0) {
           	 EnergyMeter energyMeter = new EnergyMeter();
                energyMeter.setTimeStamp(timestamp);
                energyMeter.setValue(minuteValue);

                energyMeterRepository.save(energyMeter);
            }
            else {
           	 EnergyMeter energyMeter = new EnergyMeter();
                energyMeter.setTimeStamp(timestamp);
                energyMeter.setValue(minuteValue);

                energyMeterRepository.save(energyMeter);
            }
        }
    }
    
	public EnergyMeter insertNewEnergy(){
		LocalDateTime timestamp = LocalDateTime.now();
		Random random = new Random();
        value+=(random.nextFloat() * 10);
        float minuteValue = value;
        
        EnergyMeter energyMeter = new EnergyMeter(timestamp,minuteValue);
 		return energyMeterRepository.save(energyMeter);
 	}

	public Map<LocalDateTime, Float> getHourlyConsumption() {
        List<EnergyMeter> readings = energyMeterRepository.findAllByOrderByTimeStampAsc();

        Map<LocalDateTime, Float> hourlyValues = new HashMap<>();

        int last = 0;
        Float lastValue = 0F;
        for (EnergyMeter reading : readings) {
        	
        	if(reading.getTimeStamp().getMinute() == 0 && last != reading.getTimeStamp().getHour()) {
	            LocalDateTime hourKey = reading.getTimeStamp().withMinute(0);
	            float value = reading.getValue();
	            hourlyValues.put(hourKey, (hourlyValues.getOrDefault(hourKey, 0f) + value)-lastValue);

	        	last = reading.getTimeStamp().getHour();
	        	lastValue = reading.getValue();
        	}
        	
        }
        return hourlyValues;
    }
	
	
	public List<EnergyMeter> getAllConsumption() {
        List<EnergyMeter> readings = energyMeterRepository.findAllByOrderByTimeStampAsc();
        return readings;
    }
	
	public List<ResultFormate> getConsumption() {
        List<EnergyMeter> readings = energyMeterRepository.findAllByOrderByTimeStampAsc();
        List<ResultFormate> results = new ArrayList<>();
        for (EnergyMeter energyMeter : readings) {
        	ResultFormate newResult = new ResultFormate();
        	newResult.setTimeStamp(energyMeter.getTimeStamp());
        	newResult.setHour(energyMeter.getTimeStamp().getHour());
        	newResult.setMinut(energyMeter.getTimeStamp().getMinute());
        	newResult.setValue(energyMeter.getValue());
        	results.add(newResult);
		}
        return results;
    }

}
