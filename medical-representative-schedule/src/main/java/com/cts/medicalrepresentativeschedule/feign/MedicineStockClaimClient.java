package com.cts.medicalrepresentativeschedule.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cts.medicalrepresentativeschedule.model.MedicineStock;

@FeignClient(name = "MedicineStockClaim", url = "${medicine-stock.URL}")
public interface MedicineStockClaimClient {

	@GetMapping("/FindMedicineByTreatingAilment/{treatingAilment}")
	public List<MedicineStock> getMedicineByTreatingAilment(@PathVariable String treatingAilment);
		
}
