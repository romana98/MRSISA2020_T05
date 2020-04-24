package com.project.tim05.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.tim05.model.ClinicCenterAdministrator;
import com.project.tim05.repository.ClinicCenterAdministratorRespository;;

@Service
public class ClinicCenterAdministratorService {
	
	@Autowired
	private ClinicCenterAdministratorRespository ccar;

	public void addAdminClinic(ClinicCenterAdministrator admincl) {
		ccar.save(admincl);
	}
	
	public List<ClinicCenterAdministrator> getAdminClinics(){
		return ccar.findAll();
	}

}
