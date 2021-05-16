package com.cognizant.pharmacysupply;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
@EnableFeignClients
@EnableEurekaClient
public class PharmacySupplyApplication {

	public static void main(String[] args) {
		log.info("Pharmacy Microservice Started");
		SpringApplication.run(PharmacySupplyApplication.class, args);
		log.info("Pharmacy Microservice Ends");
	}

}
