package com.cognizant.portal.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cognizant.portal.model.UserLoginCredential;
import com.cognizant.portal.model.UserToken;

@FeignClient(url="18.219.27.119:8084", name = "authorization-service")
//@FeignClient(url="localhost:8084", name = "authorization-service")
public interface AuthenticationFeignClient {

	@PostMapping(path = "/api/auth/login")
	public ResponseEntity<UserToken> login(@RequestBody UserLoginCredential usercredentials);

	@GetMapping(path = "/api/auth/validate")
	public boolean verifyToken(@RequestHeader(name = "Authorization", required = true) String token);

}
