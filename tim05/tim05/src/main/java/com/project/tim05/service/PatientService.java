package com.project.tim05.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.project.tim05.dao.PatientAccess;
import com.project.tim05.model.Patient;

@Service
public class PatientService {
	
	private final PatientAccess pa;
	
	@Autowired
	public PatientService(@Qualifier("DB") PatientAccess pa) {
		this.pa = pa;
	}
	
	public void editPatient(Patient patient) {
		pa.editPatient(patient);
	}
	
	public List<Patient> getPatients(){
		return pa.getPatients();
	}
	
	public void addPatient(Patient patient) {
		pa.addPatient(patient);
	}

}
