package com.cts.medicalrepresentativeschedule.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="medical_representatives")
public class MedicalRepresentative {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "rep_id")
	private int repId;
	
	@Column(name = "rep_name")
	private String repName;
	
	@Column(name = "rep_phone_number")
	private String repPhoneNumber;

}
