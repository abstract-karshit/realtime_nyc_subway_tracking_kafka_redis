package com.realtime.nyc.subway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NycSubwayTrackingApplication {

	public static void main(String[] args) {
		SpringApplication.run(NycSubwayTrackingApplication.class, args);
	}

}
