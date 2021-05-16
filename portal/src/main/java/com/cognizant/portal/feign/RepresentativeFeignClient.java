package com.cognizant.portal.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;


//@FeignClient(url = "localhost:8082", name = "medical-representative-schedule-service")
@FeignClient(url="18.219.27.119:8082", name = "medical-representative-schedule-service")
public interface RepresentativeFeignClient {


	@GetMapping("/api/medical-representative-schedule-service/RepSchedule/{scheduleStartDate}")
	public ResponseEntity<?> getRepSchedule(@RequestHeader(name = "Authorization") String token,
			@DateTimeFormat(pattern = "dd-MM-yyyy") @PathVariable("scheduleStartDate") String scheduleStartDate);

}
