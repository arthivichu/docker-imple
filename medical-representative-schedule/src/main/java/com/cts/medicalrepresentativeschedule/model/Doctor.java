package com.cts.medicalrepresentativeschedule.model;

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
public class Doctor {
	
	private int doctorId;
	private String doctorName;
	private String doctorContactNumber;
	private String treatingAilment;
	
}
