package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.EnergyMeter;

public interface EnergyRepository extends JpaRepository<EnergyMeter, Long> {
	EnergyMeter findTopByOrderByTimeStampDesc();
	List<EnergyMeter> findAllByOrderByTimeStampAsc();
}