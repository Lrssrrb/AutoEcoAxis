package com.entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ResultFormate {

    private LocalDateTime timeStamp;

    private int minute;
    
	private int hour;
	
	private Float value;
}
