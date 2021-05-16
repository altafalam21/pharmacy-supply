package com.cognizant.medicalrepresentativeschedule;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.medicalrepresentativeschedule.feignclient.AuthenticationFeignClient;
import com.cognizant.medicalrepresentativeschedule.feignclient.MedicineStockFeignClient;
import com.cognizant.medicalrepresentativeschedule.model.JwtResponse;
import com.cognizant.medicalrepresentativeschedule.model.MedicalRepresentative;
import com.cognizant.medicalrepresentativeschedule.model.RepSchedule;
import com.cognizant.medicalrepresentativeschedule.service.MedRepScheduleServiceImpl;
import com.cognizant.medicalrepresentativeschedule.service.MedRepServiceImpl;
import com.cognizant.medicalrepresentativeschedule.exception.TokenValidationFailedException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MedRepScheduleServiceApplicationTests {

	@Mock
	private MedRepScheduleServiceImpl medRepScheduleServiceImpl;

	@Mock
	private MedicineStockFeignClient medicineStockClient;

	@MockBean
	private AuthenticationFeignClient authFeignClient;

	@Autowired
	private MedRepServiceImpl medicalRepresentativeService;

	@Mock
	private MedicalRepresentative medicalRepresentative;

	@Test
	public void testGetRepSchedule() throws TokenValidationFailedException {
		log.info("Start");

		when(medRepScheduleServiceImpl.isValidSession("token")).thenReturn(true);
		List<RepSchedule> repSchedule = medRepScheduleServiceImpl.getRepSchedule("token", LocalDate.of(2021, 05, 15));
		assertNotNull(repSchedule);

		log.info("End");

	}

	@Test
	public void testValidateTokenFail() throws TokenValidationFailedException {

		when(authFeignClient.verifyToken("token")).thenReturn(new JwtResponse("root", "root", false));
		medRepScheduleServiceImpl.isValidSession("token");
	}

	@Test
	public void testGetMedicalRepresentatives() {
		log.info("Start");

		List<MedicalRepresentative> medicalRepresentatives = medicalRepresentativeService.getMedicalRepresentatives();
		assertNotNull(medicalRepresentatives);

		log.info("End");

	}

}