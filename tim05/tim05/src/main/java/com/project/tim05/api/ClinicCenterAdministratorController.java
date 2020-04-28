package com.project.tim05.api;

import java.util.List;

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
	public <T> ResponseEntity<T> addClinicCenterAdministrator(@RequestBody ClinicCenterAdministratorDTO cca) {
		if(check(cca) == 0)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		
		
		ClinicCenterAdministrator ccadmin = new ClinicCenterAdministrator(cca.getName(), cca.getSurname(), cca.getEmail(), cca.getPassword());
		int flag = ccas.addClinicCenterAdministrator(ccadmin);
		
		if(flag == 0)
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		else
			return ResponseEntity.status(HttpStatus.OK).body(null);
	
	}
	
	public int check(ClinicCenterAdministratorDTO cDTO) {   
		if(cDTO.getEmail() == null || cDTO.getEmail().length() == 0 || !cDTO.getEmail().matches("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")) {
			return 0;
		}
		else if(cDTO.getName() == null || cDTO.getName().length() == 0 || !cDTO.getName().matches("[a-zA-Z ]*")) {
			return 0;
		}
		else if(cDTO.getSurname() == null || cDTO.getSurname().length() == 0 || !cDTO.getSurname().matches("[a-zA-Z ]*")) {
			return 0;
		}
		else if(cDTO.getPassword() == null || cDTO.getPassword().length() < 8) {
			return 0;
		}
		
		return 1;
		
	}
	
	
}
