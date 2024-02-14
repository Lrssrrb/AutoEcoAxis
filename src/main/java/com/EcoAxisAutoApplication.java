package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EcoAxisAutoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcoAxisAutoApplication.class, args);
	}

}
