package com.cts.pharmacymedicinesupply.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.pharmacymedicinesupply.feign.MedicineStockFeignClient;
import com.cts.pharmacymedicinesupply.model.MedicineDemand;
import com.cts.pharmacymedicinesupply.model.MedicineStock;
import com.cts.pharmacymedicinesupply.model.PharmacyMedicineSupply;
import com.cts.pharmacymedicinesupply.repo.MedicineDemandRepository;
import com.cts.pharmacymedicinesupply.repo.PharmacyMedicineSupplyRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PharmacyMedicineSupplyServiceImpl implements PharmacyMedicineSupplyService {

	@Autowired
	PharmacyMedicineSupplyRepository supplyRepo;

	@Autowired
	MedicineDemandRepository demandRepo;

	@Autowired
	MedicineStockFeignClient medicineStockFeignClient;

	@Override
	public List<PharmacyMedicineSupply> getPharmacySupply(List<MedicineDemand> medicineDemandList) {

		log.info("Start");

		List<PharmacyMedicineSupply> supplyList = new ArrayList<>();

		for (MedicineDemand medicineDemand : medicineDemandList) {

			PharmacyMedicineSupply medicineSupply = new PharmacyMedicineSupply();
			medicineSupply.setMedicineName(medicineDemand.getMedicineName());
			medicineSupply.setPharmacySupplyId(medicineDemand.getDemandId());

			setSupplyCount(medicineSupply, medicineDemand);

			log.debug("Medicine Supply : ", medicineSupply);
			supplyList.add(medicineSupply);
		}

		supplyRepo.saveAll(supplyList);
		demandRepo.saveAll(medicineDemandList);

		log.debug("Supply List : {} ", supplyList);
		log.debug("Medicine DemandList : {} ", medicineDemandList);
		log.info("End");
		return supplyList;
	}

	private void setSupplyCount(PharmacyMedicineSupply medicineSupply, MedicineDemand medicineDemand) {
		log.info("Start");
		int supplyCount = 0;
		List<MedicineStock> medicines = (List<MedicineStock>) medicineStockFeignClient
				.getMedicinesByName(medicineDemand.getMedicineName());

		log.debug("Medicines By Name : {}", medicines);

		int noOfPharmacies = medicines.size();
		
		for (int i = 0; i < medicines.size(); i++) {
			supplyCount += medicines.get(i).getNumberOfTabletsInStock();

			if (supplyCount >= medicineDemand.getDemandCount()) {
				log.debug("Supply Count : ", supplyCount);

				medicineSupply.setSupplyCount(medicineDemand.getDemandCount());
				reduceCountInStock(noOfPharmacies, medicines.get(i), medicineDemand.getDemandCount());
				break;
			}

			if ((supplyCount < medicineDemand.getDemandCount()) && (i == medicines.size() - 1)) {
				log.debug("Supply Count : ", supplyCount);

				medicineSupply.setSupplyCount(supplyCount);
				reduceCountInStock(noOfPharmacies, medicines.get(i), supplyCount);
			}

		}

		setPharmacyName(medicineSupply, medicines.get(0).getPharmacyName());
		log.info("End");
	}

	private void reduceCountInStock(int noOfPharmacies, MedicineStock medicine, int supplyCount) {
		log.info("Start");
		if (medicine.getNumberOfTabletsInStock() < supplyCount)
			medicineStockFeignClient.updateNumberOfTabletsInStockByName(medicine.getMedicineName(), 0);
		else {
			int reduceCount = 0;

			reduceCount = medicine.getNumberOfTabletsInStock() - supplyCount;

			medicineStockFeignClient.updateNumberOfTabletsInStockByName(medicine.getMedicineName(),
						reduceCount);

		}
		log.info("End");
	}

	private void setPharmacyName(PharmacyMedicineSupply medicineSupply, String pharmacyName) {
		log.info("Start");
		medicineSupply.setPharmacyName(pharmacyName);
		log.info("End");
	}

}
