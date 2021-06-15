package com.cts.pharmacymedicinesupply.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.cts.pharmacymedicinesupply.model.MedicineStock;

@FeignClient(name = "MedicineStockFeign", url = "${medicine_stock.URL}")
public interface MedicineStockFeignClient {

	@PostMapping("/FindMedicineByName/{medicineName}")
	public List<MedicineStock> getMedicinesByName(@PathVariable String medicineName);

	@PostMapping("/UpdateStockCount/{medicineName}/{reduceCount}")
	public String updateNumberOfTabletsInStockByName(@PathVariable("medicineName") String medicineName,
			@PathVariable("reduceCount") int reduceCount);

}
