package com.cts.medicinestock.service;

import java.util.List;

import com.cts.medicinestock.model.MedicineStock;

public interface MedicineStockService {
	
	public List<MedicineStock> getMedicineStock(); 
	
	public List<MedicineStock> getMedicinesByTreatingAilment(String treatingAilment);

	public List<MedicineStock> getMedicineByName(String medicineName);

	public void updateNumberOfTabletsInStockByName(String medicineName, int reduceCount);

}
