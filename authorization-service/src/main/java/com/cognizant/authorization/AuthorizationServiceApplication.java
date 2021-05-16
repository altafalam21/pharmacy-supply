package com.cognizant.authorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AuthorizationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthorizationServiceApplication.class, args);
	}

}
