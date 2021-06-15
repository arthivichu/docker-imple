package com.cts.pharmacymedicinesupply;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class PharmacyMedicineSupplyApplication {

	public static void main(String[] args) {
		SpringApplication.run(PharmacyMedicineSupplyApplication.class, args);
	}

}
