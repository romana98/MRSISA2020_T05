package com.project.tim05.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.tim05.model.Clinic;
import com.project.tim05.repository.ClinicRespository;

@Service
public class ClinicService {

	@Autowired
	private ClinicRespository cr;
	
	public void addClinic(Clinic clinic) {
		cr.save(clinic);
	}
	
	public List<Clinic> getClinics(){
		return cr.findAll();
	}
}
