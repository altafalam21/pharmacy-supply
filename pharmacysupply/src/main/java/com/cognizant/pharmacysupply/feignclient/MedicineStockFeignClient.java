package com.cognizant.pharmacysupply.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cognizant.pharmacysupply.model.MedicineStock;

//@FeignClient(url = "localhost:8081", name = "medicine-stock-service")
@FeignClient(name = "medicine-stock-service")
public interface MedicineStockFeignClient {
	
	/*
	 * Method Name --> getNumberOfTabletsInStockByName
	 * @param      --> medicine name
	 * @return     --> medicines in stock
	*/
	
	@PostMapping("/api/medicine-stock/get-stock-count/{medicine}")
	public MedicineStock getNumberOfTabletsInStockByName(@RequestHeader(name = "Authorization") String token,@PathVariable("medicine") String medicine);
	
	
	/*
	 * Method Name --> updateNumberOfTabletsInStockByName
	 * @param      --> medicine name
	 * @param      --> medicine count
	 * @return     --> boolean value to show update status 
	*/
	@PostMapping("/api/medicine-stock/update-stock/{medicine}/{count}")
	public Boolean updateNumberOfTabletsInStockByName(@RequestHeader(name = "Authorization") String token,@PathVariable("medicine") String medicine, @PathVariable("count") int count);	
}
