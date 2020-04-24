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
@RequestMapping("/adminClinic")
@RestController
public class AdminClinicController {

	private final ClinicCenterAdministratorService acs;
	
	@Autowired
	public AdminClinicController(ClinicCenterAdministratorService acs) {
		this.acs = acs;
	}
	
	@GetMapping("/getAdminClinics")
	public List<ClinicCenterAdministrator> getAdminClinics(){
		return acs.getAdminClinics();
	}
	
	@PostMapping("/addAdminClinic")
	public int addDoctor(@RequestBody ClinicCenterAdministrator ac) {
		acs.addAdminClinic(ac);
		return 200;
	}
	
	
}
