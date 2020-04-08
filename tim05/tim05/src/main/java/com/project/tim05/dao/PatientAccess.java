package com.project.tim05.dao;

import java.util.List;

import com.project.tim05.model.Patient;

public interface PatientAccess {
	
	void editPatient(Patient patient);
	
	void addPatient(Patient patient);
	
	List<Patient> getPatients();

}
