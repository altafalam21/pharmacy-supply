package com.cognizant.pharmacysupply.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.pharmacysupply.exception.MedicineNotFoundException;
import com.cognizant.pharmacysupply.exception.TokenValidationFailedException;
import com.cognizant.pharmacysupply.feignclient.AuthFeignClient;
import com.cognizant.pharmacysupply.feignclient.MedicineStockFeignClient;
import com.cognizant.pharmacysupply.model.JwtResponse;
import com.cognizant.pharmacysupply.model.MedicineDemand;
import com.cognizant.pharmacysupply.model.MedicineStock;
import com.cognizant.pharmacysupply.model.PharmacyMedicineSupply;
import com.cognizant.pharmacysupply.repository.MedicineDemandRepository;
import com.cognizant.pharmacysupply.repository.PharmacyMedicineSupplyRepository;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PharmacyServiceImpl implements PharmacyService {
	private static final String MEDICINE_NOT_FOUND = "Medicine not found";

	private static final String START = "Start";

	@Autowired
	private MedicineDemandRepository medicineDemandRepo;

	@Autowired
	private PharmacyMedicineSupplyRepository pharmacyMedicineSupplyRepository;

	@Autowired
	private MedicineDemandRepository medicineDemandRepository;

	@Autowired
	private AuthFeignClient authFeign;

	@Autowired
	private MedicineStockFeignClient stockFeignClient;

	/**
	 * Gets the supply details from stock on providing demand count as input. The
	 * supply count is fetched from the medicine stock service via feign client. And
	 * if the medicine demand count is higher then the medicine supply count we are
	 * setting demand count as supply count. Method Name --> getPharmacySupplyCount
	 * 
	 * @param --> token
	 * @param --> medicineDemandList
	 * @return --> List of MedicineSupply *
	 */
	@Override
	public List<PharmacyMedicineSupply> getPharmacySupplyCount(String token, List<MedicineDemand> medicineDemandList)
			throws MedicineNotFoundException {
		log.info(START);
		log.info("Medicine Demand List {} ", medicineDemandList);
		List<PharmacyMedicineSupply> list = new ArrayList<>();

		for (MedicineDemand medicineDemand : medicineDemandList) {
			PharmacyMedicineSupply pharmacyMedicineSupply = new PharmacyMedicineSupply();
			MedicineStock medicineStock = getNumberOfTablets(token, medicineDemand);
			log.info("***********" + medicineDemand);
			log.info("{}", medicineStock);
			log.info("Medicine {} , Tablets {}", medicineDemand.getMedicineName(),
					medicineStock.getNumberOfTabletsInStock());

			if (medicineStock.getNumberOfTabletsInStock() < medicineDemand.getDemandCount()) {
				return null;
			}

			setSupply(token, pharmacyMedicineSupply, medicineDemand, medicineStock);
			list.add(pharmacyMedicineSupply);
		}
		pharmacyMedicineSupplyRepository.saveAll(list);
		medicineDemandRepository.saveAll(medicineDemandList);
		log.info("End");
		return list;
	}

	/**
	 * This method updates the stock after a successfull order has been placed
	 * Method Name --> setSupplu
	 * 
	 * @param --> token
	 * @param --> medicineSupply
	 * @param --> medicineDemand
	 * @return --> null *
	 */

	public void setSupply(String token, PharmacyMedicineSupply medicineSupply, MedicineDemand medicineDemand,
			MedicineStock medicineStock) throws MedicineNotFoundException {
		log.info(START);
		int val = 0;
		log.info("number of tablets {}", medicineStock.getNumberOfTabletsInStock());
		if (medicineStock.getNumberOfTabletsInStock() < medicineDemand.getDemandCount()) {
			medicineSupply.setSupplyCount(medicineStock.getNumberOfTabletsInStock());

		} else {
			medicineSupply.setSupplyCount(medicineDemand.getDemandCount());
			val = medicineStock.getNumberOfTabletsInStock() - medicineDemand.getDemandCount();

		}
		log.info("val = {}", val);
		try {
			stockFeignClient.updateNumberOfTabletsInStockByName(token, medicineDemand.getMedicineName(), val);
		} catch (FeignException ex) {
			throw new MedicineNotFoundException(MEDICINE_NOT_FOUND);
		}
		medicineSupply.setMedicineName(medicineDemand.getMedicineName());
		log.info("medicineDemand {} medicineSupply {}", medicineDemand, medicineSupply);
		medicineSupply.setPharmacyName(medicineStock.getPharmacyName());
		log.info("medicineSupply{}:", medicineSupply);
		log.info("End");
	}

	/**
	 * Gets the supply count from stock on giving demand count as input. supply
	 * count is fetched from the medicine stock service via feign client. And if the
	 * medicine demand count is higher then the medicine supply count we are setting
	 * demand count as supply count. Method Name --> getNumberOfTablets
	 * 
	 * @param --> token
	 * @param --> medicineDemandList
	 * @return --> List of MedicineSupply
	 */

	public MedicineStock getNumberOfTablets(String token, MedicineDemand medicineDemand)
			throws MedicineNotFoundException {
		log.info(START);
		MedicineStock medicineStock = null;
		log.info("Medicine : {}", medicineDemand);
		try {
			medicineStock = stockFeignClient.getNumberOfTabletsInStockByName(token, medicineDemand.getMedicineName());
		} catch (FeignException ex) {
			throw new MedicineNotFoundException(MEDICINE_NOT_FOUND);
		}

		if (medicineStock == null) {
			throw new MedicineNotFoundException(MEDICINE_NOT_FOUND);
		}
		log.info("End");
		return medicineStock;
	}

	/**
	 * From the database we are fetching the MedicineDemand. It invokes the
	 * findAll() method which is present in the JpaRepository interface. 
	 * Method Name --> getMedicineDemand
	 * 
	 * @return --> List of medicine demand
	 */
	@Override
	public List<MedicineDemand> getMedicineDemand() {
		log.info(START);
		return medicineDemandRepo.findAll();
	}

	/**
	 * From the database we are fetching all the. It invokes the findAll() method
	 * which is present in the JPARepository interface.
	 * Method Name --> getMedicineSupply
	 * 
	 * @return --> List of medicine supply
	 */

	@Override
	public List<PharmacyMedicineSupply> getMedicineSupply() {
		log.info(START);
		return pharmacyMedicineSupplyRepository.findAll();
	}

	/**
	 * Validate the token received inside the Authorization part of the header
	 * Method Name --> validateToken
	 * 
	 * @return --> List of medicine supply
	 */
	@Override
	public Boolean validateToken(String token) {
		log.info("Validating token");
		JwtResponse jwtResponse = authFeign.verifyToken(token);

		if (jwtResponse.isValid())
			return true;
		throw new TokenValidationFailedException("Token is no longer valid");
	}
}
