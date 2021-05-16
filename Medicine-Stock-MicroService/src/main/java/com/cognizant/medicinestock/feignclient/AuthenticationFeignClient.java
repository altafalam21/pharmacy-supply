package com.cognizant.medicinestock.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cognizant.medicinestock.model.JwtResponse;

//@FeignClient(name = "authorization-service", url ="localhost:8084")
@FeignClient(name = "authorization-service")
public interface AuthenticationFeignClient {


	@GetMapping(path = "/api/auth/validate")
	public JwtResponse verifyToken(@RequestHeader(name = "Authorization", required = true) String token);

}
