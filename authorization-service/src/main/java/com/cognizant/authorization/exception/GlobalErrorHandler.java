package com.cognizant.authorization.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cognizant.authorization.model.ErrorResponse;

import lombok.extern.slf4j.Slf4j;

/**
 *  This class is used to handle all the errors. All the data about exception is populated into Error Response
 *  and the it is sent in response.
 *  We have use annotation RestControllerAdvice and extend ResponseEntityExceptionHandler
 *  All the methods in the class
 * corresponds to an Exception and returns an object of ErrorResponse class with
 * fields like time stamp, message etc.
 *
 */

@Slf4j
@RestControllerAdvice
public class GlobalErrorHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleAllErrors(Exception ee) {
		log.info("START");
		ErrorResponse response = new ErrorResponse();
		response.setTimestamp(LocalDateTime.now());
		response.setMessage(ee.getMessage());
		response.setStatus(HttpStatus.BAD_REQUEST);
		response.setReason("BAD REQUEST");
		log.debug("ERROR RESPONSE {}:", response);
		log.info("END");
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InternalAuthenticationServiceException.class)
	public ResponseEntity<ErrorResponse> handleInternalAuthenticationServiceException(
			InternalAuthenticationServiceException iase) {
		log.info("START");
		ErrorResponse response = new ErrorResponse();
		response.setTimestamp(LocalDateTime.now());
		response.setMessage(iase.getMessage());
		response.setStatus(HttpStatus.FORBIDDEN);
		response.setReason("ENTERED CREDENTIALS MIGHT BE WRONG");
		log.debug("ERROR RESPONSE{}:", response);
		log.info("END");
		return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
	}
	

}
