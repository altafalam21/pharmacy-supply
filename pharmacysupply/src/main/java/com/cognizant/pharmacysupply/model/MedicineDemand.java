package com.cognizant.pharmacysupply.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "medicine_demand")
public class MedicineDemand {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@NotEmpty(message = "Medicine name must have a value")
	@NotNull(message = "Medicine name must not be null")
	private String medicineName;

	private int demandCount;

}
