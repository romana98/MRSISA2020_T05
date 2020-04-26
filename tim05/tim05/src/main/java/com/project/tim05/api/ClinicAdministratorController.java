package com.project.tim05.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.project.tim05.dto.ClinicAdministratorDTO;
import com.project.tim05.model.ClinicAdministrator;
import com.project.tim05.service.ClinicAdministratorService;

public class ClinicAdministratorController {
	
private final ClinicAdministratorService cas;
	
	@Autowired
	public ClinicAdministratorController(ClinicAdministratorService cas) {
		this.cas = cas;
	}
	
	@GetMapping("/getClinicAdministrators")
	public List<ClinicAdministrator> getClinicAdministrator(){
		return cas.getClinicAdministrators();
	}
	
	@PostMapping("/addClinicAdministrator")
	public int addClinicAdministrator(@RequestBody ClinicAdministratorDTO cca) {
		ClinicAdministrator cadmin = new ClinicAdministrator();
		cas.addClinicAdministrator(cadmin);
		return 200;
	}
	

}
