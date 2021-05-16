package com.cognizant.medicalrepresentativeschedule.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

//@FeignClient(url = "localhost:8081", name = "medicine-stock-service")
@FeignClient(name = "medicine-stock-service")
public interface MedicineStockFeignClient {

	@PostMapping("/api/medicine-stock/byTreatingAilment/{treatingAilment}")
	public String[] getMedicinesByTreatingAilment(@RequestHeader("Authorization") String token,
			@PathVariable("treatingAilment") String treatingAilment);

}
