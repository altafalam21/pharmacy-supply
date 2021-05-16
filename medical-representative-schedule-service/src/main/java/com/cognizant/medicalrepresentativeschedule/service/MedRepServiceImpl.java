package com.cognizant.medicalrepresentativeschedule.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.medicalrepresentativeschedule.dao.MedRepRepository;
import com.cognizant.medicalrepresentativeschedule.model.MedicalRepresentative;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MedRepServiceImpl implements MedRepService {

	@Autowired
	private MedRepRepository medicalRepresentativesRepository;

	@Override
	public List<MedicalRepresentative> getMedicalRepresentatives() {

		log.info("Start");

		return medicalRepresentativesRepository.findAll();

	}

}
