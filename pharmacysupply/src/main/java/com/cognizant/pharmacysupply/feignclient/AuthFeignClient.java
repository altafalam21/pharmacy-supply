package com.cognizant.pharmacysupply.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cognizant.pharmacysupply.model.JwtResponse;

//@FeignClient(name = "authorization-service", url = "localhost:8084")
@FeignClient(name = "authorization-service")
public interface AuthFeignClient {
	
	/*
	 * Method Name --> verifyToken
	 * @param      --> Token  
	 * verifies the token for authorization
	 */

	@GetMapping(value = "/api/auth/validate")
	public JwtResponse verifyToken(@RequestHeader(name = "Authorization", required = true) String token);

}
