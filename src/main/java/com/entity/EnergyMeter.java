package com.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class EnergyMeter {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	private Float value;

    private LocalDateTime timeStamp;

	public EnergyMeter(LocalDateTime timeStamp, Float value) {
		super();
		this.timeStamp = timeStamp;
		this.value = value;
	}
}
