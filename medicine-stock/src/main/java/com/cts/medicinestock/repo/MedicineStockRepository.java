package com.cts.medicinestock.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cts.medicinestock.model.MedicineStock;

@Repository
public interface MedicineStockRepository extends JpaRepository<MedicineStock, Integer> {

	//@Query(value = "SELECT m FROM MedicineStock m WHERE m.targetAilment = :treatingAilment")
	@Query(value = "SELECT * FROM medicine_stock m WHERE m.target_ailment = ?1", nativeQuery = true)
	List<MedicineStock> findByTreatingAilment(@Param(value = "treatingAilment") String treatingAilment);

	//@Query(value = "SELECT m FROM MedicineStock m WHERE m.medicineName = ?1")
	@Query(value = "SELECT * FROM medicine_stock m WHERE m.medicine_name = ?1", nativeQuery = true)
	List<MedicineStock> getMedicineByName(String medicineName);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE medicine_stock m SET m.number_of_tablets_in_stock = ?2 WHERE m.medicine_name = ?1", nativeQuery = true)
	void updateStockCountByName(String medicineName, int reduceCount);
	
}
