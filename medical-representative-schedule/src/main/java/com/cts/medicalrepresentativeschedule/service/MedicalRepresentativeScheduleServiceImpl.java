package com.cts.medicalrepresentativeschedule.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.medicalrepresentativeschedule.Repo.MedicalRepresentativeRepository;
import com.cts.medicalrepresentativeschedule.feign.MedicineStockFeignClient;
import com.cts.medicalrepresentativeschedule.model.Doctor;
import com.cts.medicalrepresentativeschedule.model.MedicalRepresentative;
import com.cts.medicalrepresentativeschedule.model.MedicineStock;
import com.cts.medicalrepresentativeschedule.model.RepSchedule;
import com.cts.medicalrepresentativeschedule.util.CsvUtil;
import com.cts.medicalrepresentativeschedule.util.DateUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MedicalRepresentativeScheduleServiceImpl implements MedicalRepresentativeScheduleService {

	@Autowired
	MedicalRepresentativeRepository repo;
	
	@Autowired
	MedicineStockFeignClient medicineStockClient;

	@Override
	public List<RepSchedule> getRepSchedule(String scheduledDate) {

		// hard-coded value
//		RepSchedule s1 = new RepSchedule("john", "dr.ishan", "1 to 2 pm", date, "123456789");

		List<RepSchedule> repSchedules = new ArrayList<>();

		// doctors
		List<Doctor> doctors = CsvUtil.getDoctors();
		log.debug("Doctors : {}", doctors);

		// medical rep's
		List<MedicalRepresentative> medicalRepresentatives = repo.findAll();
		log.debug("Medical Representative : {}", medicalRepresentatives);

		LocalDate scheduledLocalDate = DateUtil.getLocalDate(scheduledDate);
		log.debug("Scheduled Start LocalDate", scheduledLocalDate.toString());

		LocalDate today = LocalDate.now();
		if (scheduledLocalDate.isBefore(today))
			return repSchedules;

		// representative schedule calculation
		for (int i = 0; i < doctors.size(); i++) {

			if (scheduledLocalDate.getDayOfWeek() == DayOfWeek.SUNDAY)
				scheduledLocalDate = scheduledLocalDate.plusDays(1);

			Doctor doctor = doctors.get(i);
			MedicalRepresentative medicalRep = medicalRepresentatives.get(i%3);

			RepSchedule repSchedule = new RepSchedule();
			repSchedule.setRepName(medicalRep.getRepName());
			repSchedule.setDoctorName(doctor.getDoctorName());
			repSchedule.setTreatingAilment(doctor.getTreatingAilment());
			repSchedule.setMeetingSlot("1 PM to 2PM");
			repSchedule.setMeetingDate(scheduledLocalDate);
			repSchedule.setDoctorContactNumber(doctor.getDoctorContactNumber());

			// invoke medicine stock here
			List<MedicineStock> medicineList = medicineStockClient.getMedicineByTreatingAilment(doctor.getTreatingAilment());
			String[] medicinesByTreatingAilment = new String[medicineList.size()];
			int index = 0;
			for(MedicineStock medicine : medicineList) 
				medicinesByTreatingAilment[index++] = medicine.getMedicineName();
			
			repSchedule.setMedicines(medicinesByTreatingAilment);
			
			log.debug("Rep Schedule : {}", repSchedule);
			
			repSchedules.add(repSchedule);
			scheduledLocalDate = scheduledLocalDate.plusDays(1);
		}

		log.debug("Rep Schedules : {}", repSchedules);
		
		log.info("End");
		return repSchedules;
	}

}
