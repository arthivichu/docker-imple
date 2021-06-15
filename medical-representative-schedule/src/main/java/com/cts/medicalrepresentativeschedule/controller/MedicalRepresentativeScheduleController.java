package com.cts.medicalrepresentativeschedule.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.medicalrepresentativeschedule.model.Doctor;
import com.cts.medicalrepresentativeschedule.model.MedicalRepresentative;
import com.cts.medicalrepresentativeschedule.model.RepSchedule;
import com.cts.medicalrepresentativeschedule.service.MedicalRepresentativeScheduleService;
import com.cts.medicalrepresentativeschedule.service.MedicalRepresentativeService;
import com.cts.medicalrepresentativeschedule.util.CsvUtil;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/RepresentativeSchedule")
public class MedicalRepresentativeScheduleController {

	@Autowired
	MedicalRepresentativeScheduleService repScheduleService;

	@Autowired
	MedicalRepresentativeService medicalRepresentativeService;

	@GetMapping("/RepSchedule/{scheduleStartDate}")
	@ApiOperation(notes = "Returns list of RepSchedule", value = "Find Rep Schedule")
	public ResponseEntity<List<RepSchedule>> getRepSchedule(@RequestHeader(name = "Authorization", required = true) String requestTokenHeader,
			@ApiParam(name = "scheduleStartDate", value = "schedule start date of the meeting") @PathVariable String scheduleStartDate) {
		log.info("Start");
		log.debug("schedule start date", scheduleStartDate);

		List<RepSchedule> repSchedule = new ArrayList<>();
		repSchedule = repScheduleService.getRepSchedule(scheduleStartDate);

		log.debug("Rep Schedule : {}", repSchedule);
		log.info("End");
		// return repSchedule;
		return ResponseEntity.of(Optional.of(repSchedule));
	}

	@GetMapping("/medical_representatives")
	@ApiOperation(notes = "Returns list of medical representatives", value = "Find medical representatives")
	public ResponseEntity<List<MedicalRepresentative>> getMedicalRepresentative(@RequestHeader(name = "Authorization", required = true) String requestTokenHeader) {
		log.info("Start");
		List<MedicalRepresentative> medicalRepresentative = new ArrayList<>();

		medicalRepresentative = medicalRepresentativeService.getMedicalRepresentative();
		log.debug("Medical Representatives : {}", medicalRepresentative);

		log.info("End");
		return ResponseEntity.of(Optional.of(medicalRepresentative));

	}
	
	@GetMapping("/Doctors")
	@ApiOperation(notes = "Returns list of doctors", value = "Find Doctors")
	public ResponseEntity<List<Doctor>> getDoctors(@RequestHeader(name = "Authorization", required = true) String requestTokenHeader) {
		log.info("Start");
		List<Doctor> doctors = CsvUtil.getDoctors();
		
		log.debug("Doctors : {}", doctors);		
		log.info("End");
		return ResponseEntity.of(Optional.of(doctors));
	}
}
