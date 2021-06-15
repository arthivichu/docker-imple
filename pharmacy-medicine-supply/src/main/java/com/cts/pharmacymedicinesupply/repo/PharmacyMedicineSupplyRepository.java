package com.cts.pharmacymedicinesupply.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.pharmacymedicinesupply.model.PharmacyMedicineSupply;

@Repository
public interface PharmacyMedicineSupplyRepository extends JpaRepository<PharmacyMedicineSupply,Integer> {

}
