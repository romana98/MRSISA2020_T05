package com.project.tim05.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.tim05.model.ClinicCenterAdministrator;
import com.project.tim05.service.ClinicCenterAdministratorService;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/clinicCenterAdministrator")
@RestController
public class ClinicCenterAdministratorController {

	private final ClinicCenterAdministratorService ccas;
	
	@Autowired
	public ClinicCenterAdministratorController(ClinicCenterAdministratorService ccas) {
		this.ccas = ccas;
	}
	
	@GetMapping("/getClinicCenterAdministrator")
	public List<ClinicCenterAdministrator> getClinicCenterAdministrator(){
		return ccas.getClinicCenterAdministrators();
	}
	
	@PostMapping("/addClinicCenterAdministrator")
	public int addClinicCenterAdministrator(@RequestBody ClinicCenterAdministrator ac) {
		ccas.addClinicCenterAdministrator(ac);
		return 200;
	}
	
	
}
