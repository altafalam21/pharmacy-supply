package com.cognizant.authorization.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
   	Jwt Response is not just about returning true or false 
	its about returning Principal which is the info about the logged in user as well.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JwtResponse {

	private String userid;

	private String username;

	private boolean isValid;
}
