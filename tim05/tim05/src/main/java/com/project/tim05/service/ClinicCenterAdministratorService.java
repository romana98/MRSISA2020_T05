package com.project.tim05.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.tim05.model.ClinicCenterAdministrator;
import com.project.tim05.repository.ClinicCenterAdministratorRespository;;

@Service
public class ClinicCenterAdministratorService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ClinicCenterAdministratorRespository ccar;

	public int addClinicCenterAdministrator(ClinicCenterAdministrator admincl) {
		try {
			
			admincl.setPassword(passwordEncoder.encode(admincl.getPassword()));
			ccar.save(admincl);
			
		} catch (Exception e) {
			
			return 0;
		}
		
		return 1;
	}
	
	public List<ClinicCenterAdministrator> getClinicCenterAdministrators(){
		return ccar.findAll();
	}

}
