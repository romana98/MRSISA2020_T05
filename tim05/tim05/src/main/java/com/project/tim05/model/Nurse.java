package com.project.tim05.model;

import java.util.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Nurse extends MedicalStaff {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Set<Medicine> medicine;
	
	public Nurse() {
		super();
	}

	public Nurse(Set<Medicine> medicine) {
		super();
		this.medicine = medicine;
	}

	public Set<Medicine> getMedicine() {
		return medicine;
	}

	public void setMedicine(Set<Medicine> medicine) {
		this.medicine = medicine;
	}
}