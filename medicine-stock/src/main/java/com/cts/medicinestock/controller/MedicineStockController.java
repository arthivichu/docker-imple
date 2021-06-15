package com.cts.medicinestock.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.medicinestock.model.MedicineStock;
import com.cts.medicinestock.service.MedicineStockService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/MedicineStock")
@Slf4j
public class MedicineStockController {

	@Autowired
	MedicineStockService medicineStockService;

	@GetMapping("/MedicineStockInformation")
	@ApiOperation(notes = "Returns list of MedicineStock", value = "Find medicine stock")
	public ResponseEntity<List<MedicineStock>> getMedicalStockInformation(@RequestHeader(name = "Authorization", required = true) String requestTokenHeader) {

		log.info("Start");
		List<MedicineStock> medicineStock = new ArrayList<>();

		medicineStock = medicineStockService.getMedicineStock();

		log.debug("MedicineStock : {}", medicineStock);
		log.info("end");
		// return medicineStock;
		return ResponseEntity.of(Optional.of(medicineStock));

	}

	@GetMapping("/FindMedicineByTreatingAilment/{treatingAilment}")
	@ApiOperation(notes = "Returns medicine based on treating ailment category", value = "Find medicine stock by treating ailment")
	public ResponseEntity<List<MedicineStock>> getMedicineByTreatingAilment(@RequestHeader(name = "Authorization", required = true) String requestTokenHeader,
			@ApiParam(name = "treatingAilment", value = "treating Ailment of medicine") @PathVariable String treatingAilment) {
		log.info("Start");
		List<MedicineStock> medicineByTreatingAilment = new ArrayList<>();
		
		medicineByTreatingAilment = medicineStockService.getMedicinesByTreatingAilment(treatingAilment);
		
		log.debug("Medicine By Treating Ailment : {}", medicineByTreatingAilment);
		log.info("end");
		return ResponseEntity.of(Optional.of(medicineByTreatingAilment));
	}
	
	@PostMapping("/FindMedicineByName/{medicineName}")
	@ApiOperation(notes = "Returns medicine by name", value = "Find Medicine By Name")
	public ResponseEntity<List<MedicineStock>> getMedicinesByName(@RequestHeader(name = "Authorization", required = true) String requestTokenHeader,
			@ApiParam(name = "medicineName", value = "name of medicine") @PathVariable String medicineName) {
		log.info("Start");
		
		List<MedicineStock> medicineStock = medicineStockService.getMedicineByName(medicineName);
		
		log.debug("Medicine By Medicine Name : {}", medicineStock);
		log.info("end");
		return ResponseEntity.of(Optional.of(medicineStock));
	}
	
	@PostMapping("/UpdateStockCount/{medicineName}/{reduceCount}")
	@ApiOperation(notes = "Reduces Stock Count By Name", value = "Update Medicine Stock")
	public ResponseEntity<String> updateNumberOfTabletsInStockByName(@RequestHeader(name = "Authorization", required = true) String requestTokenHeader,
			@ApiParam(name = "medicineName", value = "name of medicine") @PathVariable("medicineName") String medicineName,
			@ApiParam(name = "reduceCount", value = "count of medicine") @PathVariable("reduceCount") int reduceCount) {

		log.info("START");

		medicineStockService.updateNumberOfTabletsInStockByName(medicineName, reduceCount);
		
		log.info("Updated");
		log.info("END");
		return new ResponseEntity<String>("Stock Count Updated", HttpStatus.OK);
	}
	
}
