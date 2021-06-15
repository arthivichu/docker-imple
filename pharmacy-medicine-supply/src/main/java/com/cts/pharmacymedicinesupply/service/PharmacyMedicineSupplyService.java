package com.cts.pharmacymedicinesupply.service;

import java.util.List;

import com.cts.pharmacymedicinesupply.model.MedicineDemand;
import com.cts.pharmacymedicinesupply.model.PharmacyMedicineSupply;

public interface PharmacyMedicineSupplyService {

	List<PharmacyMedicineSupply> getPharmacySupply(List<MedicineDemand> medicineDemandList);
	
}
