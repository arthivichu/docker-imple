package com.cts.medicalrepresentativeschedule.service;

import java.util.List;

import com.cts.medicalrepresentativeschedule.model.RepSchedule;

public interface MedicalRepresentativeScheduleService {
	
	public List<RepSchedule> getRepSchedule(String scheduleDate);
}
