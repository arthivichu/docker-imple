package com.cts.pharmacymedicinesupply.model;

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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "medicine_supply")
public class PharmacyMedicineSupply {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "supply_id")
	private int pharmacySupplyId;

	@Column(name = "pharmacy_name")
	private String pharmacyName;
	
	@Column(name = "medicine_name")
	private String medicineName;

	@Column(name = "supply_count")
	private int supplyCount;

}
