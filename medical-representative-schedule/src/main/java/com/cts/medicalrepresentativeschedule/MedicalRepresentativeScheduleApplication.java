package com.cts.medicalrepresentativeschedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MedicalRepresentativeScheduleApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicalRepresentativeScheduleApplication.class, args);
	}

}
