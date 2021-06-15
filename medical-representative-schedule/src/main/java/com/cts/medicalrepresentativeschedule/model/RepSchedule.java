package com.cts.medicalrepresentativeschedule.model;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RepSchedule {

	private String repName;
	private String doctorName;
	private String treatingAilment;
	private String[] medicines;
	private String meetingSlot;
	private LocalDate meetingDate;
	private String doctorContactNumber;

}
