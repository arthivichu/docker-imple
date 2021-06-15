package com.cts.medicinestock.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

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
@Entity
@Table(name = "medicine_stock")
public class MedicineStock {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "medicine_id")
	private int medicineId;

	@Column(name = "medicine_name")
	private String medicineName;

	@Column(name = "chemical_composition")
	private String chemicalComposition;

	@Column(name = "target_ailment")
	private String targetAilment;

	@Column(name = "pharmacy_name")
	private String pharmacyName;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "date_of_expiry")
	private Date dateOfExpiry;

	@Column(name = "number_of_tablets_in_stock")
	private int numberOfTabletsInStock;

}
