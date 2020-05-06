package com.project.tim05.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.tim05.dto.ClinicCenterAdministratorDTO;
import com.project.tim05.model.ClinicCenterAdministrator;
import com.project.tim05.model.RegistrationRequest;
import com.project.tim05.model.User;
import com.project.tim05.service.ClinicCenterAdministratorService;
import com.project.tim05.service.RegistrationRequestService;
import com.project.tim05.service.UserService;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/clinicCenterAdministrator")
@RestController
public class ClinicCenterAdministratorController<T> {
	
	@Autowired
	private UserService userService;

	private final ClinicCenterAdministratorService ccas;
	private final RegistrationRequestService rs;
	
	@Autowired
	public ClinicCenterAdministratorController(ClinicCenterAdministratorService ccas, RegistrationRequestService rs) {
		this.ccas = ccas;
		this.rs = rs;
	}
	
	@PostMapping("/addClinicCenterAdministrator")
	@PreAuthorize("hasRole('CLINIC_CENTER_ADMIN')")
	public ResponseEntity<T> addClinicCenterAdministrator(@Valid @RequestBody ClinicCenterAdministratorDTO cca) {
		
		User existUser = this.userService.findByEmail(cca.getEmail());
		RegistrationRequest existUser1 = this.rs.findByEmail(cca.getEmail());
		if (existUser != null || existUser1 != null) {
			 ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		}
		
		
		ClinicCenterAdministrator ccadmin = new ClinicCenterAdministrator(cca.getName(), cca.getSurname(), cca.getEmail(), cca.getPassword());
		int flag = ccas.addClinicCenterAdministrator(ccadmin);
		
		if(flag == 0)
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		else
			return ResponseEntity.status(HttpStatus.OK).body(null);
	
	}
	
}
