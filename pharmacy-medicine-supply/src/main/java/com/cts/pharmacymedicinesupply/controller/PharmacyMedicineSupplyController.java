package com.cts.pharmacymedicinesupply.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.pharmacymedicinesupply.model.MedicineDemand;
import com.cts.pharmacymedicinesupply.model.PharmacyMedicineSupply;
import com.cts.pharmacymedicinesupply.service.PharmacyMedicineSupplyService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/PharmacyMedicineSupply")
public class PharmacyMedicineSupplyController {

	@Autowired
	PharmacyMedicineSupplyService supplyService;

	@PostMapping("/PharmacySupply")
	@ApiOperation(notes = "Returns List of Pharmacy Medicine Supply", value = "Find Pharmacy Medicine Supply")
	public ResponseEntity<List<PharmacyMedicineSupply>> getPharmacySupply(@RequestHeader(name = "Authorization", required = true) String requestTokenHeader,
			@ApiParam(name = "medicineDemandList", value = "medicine demand list to get medicine supply list") @RequestBody List<MedicineDemand> medicineDemandList) {

		log.info("Start");
		List<PharmacyMedicineSupply> supplyList;

		supplyList = supplyService.getPharmacySupply(medicineDemandList);

		log.debug("Supply List", supplyList);
		log.info("End");
		// return supplyList;
		return ResponseEntity.of(Optional.of(supplyList));
	}

}
