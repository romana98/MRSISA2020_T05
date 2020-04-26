package com.project.tim05.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.tim05.dto.ClinicAdministratorDTO;
import com.project.tim05.model.ClinicAdministrator;
import com.project.tim05.service.ClinicAdministratorService;
import com.project.tim05.service.ClinicService;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/clinicAdministrator")
@RestController
public class ClinicAdministratorController {
	
	private final ClinicAdministratorService cas;
	private final ClinicService cs;
	
	@Autowired
	public ClinicAdministratorController(ClinicAdministratorService cas, ClinicService cs) {
		this.cas = cas;
		this.cs = cs;
	}
	
	@GetMapping("/getClinicAdministrators")
	public List<ClinicAdministrator> getClinicAdministrator(){
		return cas.getClinicAdministrators();
	}
	
	@PostMapping("/addClinicAdministrator")
	public int addClinicAdministrator(@RequestBody ClinicAdministratorDTO cca) {
		ClinicAdministrator cadmin = new ClinicAdministrator(cca.getName(), cca.getSurname(), cca.getEmail(), cca.getPassword(), cs.getClinic(cca.getClinic()));
		cas.addClinicAdministrator(cadmin);
		return 200;
	}
	

}
