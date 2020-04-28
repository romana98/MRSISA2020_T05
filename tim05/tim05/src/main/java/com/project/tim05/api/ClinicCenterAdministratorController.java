package com.project.tim05.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.tim05.dto.ClinicCenterAdministratorDTO;
import com.project.tim05.model.ClinicCenterAdministrator;
import com.project.tim05.service.ClinicCenterAdministratorService;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/clinicCenterAdministrator")
@RestController
public class ClinicCenterAdministratorController<T> {

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
	public ResponseEntity<T> addClinicCenterAdministrator(@Valid @RequestBody ClinicCenterAdministratorDTO cca) {
		
		ClinicCenterAdministrator ccadmin = new ClinicCenterAdministrator(cca.getName(), cca.getSurname(), cca.getEmail(), cca.getPassword());
		int flag = ccas.addClinicCenterAdministrator(ccadmin);
		
		if(flag == 0)
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		else
			return ResponseEntity.status(HttpStatus.OK).body(null);
	
	}
	
}
