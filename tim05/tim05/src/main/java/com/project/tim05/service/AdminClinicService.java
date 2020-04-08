package com.project.tim05.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.project.tim05.dao.AdminClinicAccess;
import com.project.tim05.model.AdminClinic;

@Service
public class AdminClinicService {
	
	private final AdminClinicAccess aca;

	@Autowired
	public AdminClinicService(@Qualifier("DB") AdminClinicAccess aca) {
		this.aca = aca;
	}
	
	public void addAdminClinic(AdminClinic admincl) {
		aca.addAdminClinic(admincl);
	}
	
	public List<AdminClinic> getAdminClinics(){
		return aca.getAdminClinics();
	}

}
