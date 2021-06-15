package com.cts.medicinestock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.medicinestock.model.MedicineStock;
import com.cts.medicinestock.repo.MedicineStockRepository;

@Service
public class MedicineStockServiceImpl implements MedicineStockService {

	@Autowired
	MedicineStockRepository repo;
	
	@Override
	public List<MedicineStock> getMedicineStock() {

//		MedicineStock s1 = new MedicineStock(1,"crocin", "cc", "general", "p1", Date.valueOf("2021-06-06"), 10);

//		List<MedicineStock> dummyList = new ArrayList<>();
//		dummyList.add(s1);

		return repo.findAll();
	}

	// This method is invoked by repSchedule service to schedule meeting
	@Override
	public List<MedicineStock> getMedicinesByTreatingAilment(String treatingAilment) {
		
		return repo.findByTreatingAilment(treatingAilment);
	}

	@Override
	public List<MedicineStock> getMedicineByName(String medicineName) {
		return repo.getMedicineByName(medicineName);
	}

	@Override
	public void updateNumberOfTabletsInStockByName(String medicineName, int reduceCount) {
		repo.updateStockCountByName(medicineName, reduceCount);
	}

	

}
