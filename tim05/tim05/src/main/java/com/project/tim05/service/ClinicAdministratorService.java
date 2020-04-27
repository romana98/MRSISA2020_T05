package com.project.tim05.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.tim05.model.ClinicAdministrator;
import com.project.tim05.repository.ClinicAdministratorRespository;

@Service
public class ClinicAdministratorService {

	@Autowired
	private ClinicAdministratorRespository car;
	
	public void addClinicAdministrator(ClinicAdministrator admincl) {
		car.save(admincl);
	}
	
	public List<ClinicAdministrator> getClinicAdministrators(){
		return car.findAll();
	}
}
