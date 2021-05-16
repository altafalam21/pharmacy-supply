package com.cognizant.authorization.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * THis is the entity which will be mapped to the databsae.
 */

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name="user")
public class MyUser {


	@Id
	@Column(name="userid", length=20)
	private String userid;
	
	@Column(name="password", length=20)
	private String password;
	
	@Column(name="username", length=20)
	private String username;

}
