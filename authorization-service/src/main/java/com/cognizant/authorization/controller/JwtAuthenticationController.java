package com.cognizant.authorization.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.authorization.model.JwtResponse;
import com.cognizant.authorization.model.UserLoginCredential;
import com.cognizant.authorization.model.UserToken;
import com.cognizant.authorization.repository.MyUserRepository;
import com.cognizant.authorization.service.CustomerDetailsService;
import com.cognizant.authorization.util.JwtUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * This is the controller class for creating and validating JWT Tokens.
 */

@Slf4j
@RestController
public class JwtAuthenticationController {

	@Autowired
	private JwtUtil jwtutil;

	@Autowired
	private CustomerDetailsService customerDetailService;

	@Autowired
	private MyUserRepository userRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	/**
	 * This method takes userId and Password as parameters and checks if its correct
	 * by invoking authenticate method of {@link AuthenticationManager} class and
	 * throws exception if found incorrect.
	 * 
	 * @param userid
	 * @param password
	 * @throws Exception
	 */

	@PostMapping("/login")
	public ResponseEntity<UserToken> login(@RequestBody UserLoginCredential userLoginCredentials) throws Exception {
		log.info("START");
		String userid = userLoginCredentials.getUserid();
		String password = userLoginCredentials.getPassword();
		log.debug("USERID AND PASSWORD {}:", userid, password);
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userid, password));
		} catch (DisabledException e) {
			log.error("EXCEPTION: NOT A VALID USER");
			throw new Exception("USER DISABLED", e);
		} catch (BadCredentialsException e) {
			log.error("EXCEPTION: NOT A VALID USER");
			throw new Exception("INVALID_CREDENTIALS", e);
		}
		final UserDetails userdetails = customerDetailService.loadUserByUsername(userLoginCredentials.getUserid());
		log.debug("USERDETAILS {}:", userdetails);
		log.info("END");
		return new ResponseEntity<>(new UserToken(userLoginCredentials.getUserid(), 
				jwtutil.generateToken(userdetails)),HttpStatus.OK);
	}

	@GetMapping("/validate")
	public ResponseEntity<?> getValidity(@RequestHeader("Authorization") final String token) {
		log.debug("TOKEN : ", token);
		String newToken = token.substring(7);
		log.debug("TOKEN AFTER REMOVING BEARER : ", newToken);
		JwtResponse jwtResponse = new JwtResponse();
		boolean tokenValidation = jwtutil.validateToken(newToken);
		if (tokenValidation) {
			log.info("TOKEN IS VALID");
			jwtResponse.setUserid(jwtutil.extractUsername(newToken));
			jwtResponse.setValid(true);
			jwtResponse
					.setUsername((userRepository.findById(jwtutil.extractUsername(newToken))
					.orElse(null).getUsername()));
			log.debug("JWTRESPONSE {}:", jwtResponse);
		} else {
			log.error("TOKEN VALIDATION FAILED");
			jwtResponse.setValid(false);
		}
		log.info("END");
		return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
	}

}
