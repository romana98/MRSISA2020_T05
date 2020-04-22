package com.project.tim05.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.tim05.model.AdminClinic;
import com.project.tim05.repository.AdminClinicRespository;

@Service
public class AdminClinicService {
	
	@Autowired
	private AdminClinicRespository aca;

	public void addAdminClinic(AdminClinic admincl) {
		aca.save(admincl);
	}
	
	public List<AdminClinic> getAdminClinics(){
		return aca.findAll();
	}

}
