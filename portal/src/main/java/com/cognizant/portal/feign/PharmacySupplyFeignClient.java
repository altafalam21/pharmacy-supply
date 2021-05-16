package com.cognizant.portal.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cognizant.portal.model.MedicineDemand;


//@FeignClient(url = "localhost:8083", name = "pharmacy-medicine-supply")
@FeignClient(url="18.219.27.119:8083", name = "pharmacy-supply-service")
public interface PharmacySupplyFeignClient {


	@RequestMapping("/api/pharmacy-medicine-supply/pharmacy-supply")
	public ResponseEntity<?> getPharmacySupply(@RequestHeader(name = "Authorization") String token,
			@RequestBody List<MedicineDemand> medicineDemandList);


	@RequestMapping("/api/pharmacy-medicine-supply/getMedicineSupply")
	public ResponseEntity<?> getMedicineSupply(@RequestHeader(name = "Authorization") String token);

	@RequestMapping("/api/pharmacy-medicine-supply/getMedicineDemand")
	public ResponseEntity<?> getMedicineDemand(@RequestHeader(name = "Authorization") String token);

}
