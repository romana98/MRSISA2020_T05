package com.project.tim05.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.tim05.dto.RegistrationRequestDTO;
import com.project.tim05.model.Patient;
import com.project.tim05.model.RegistrationRequest;
import com.project.tim05.service.RegistrationRequestService;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/registrationRequests")
@RestController
public class RegistrationRequestController {
	
	private final RegistrationRequestService rrs;
	
	@Autowired
	public RegistrationRequestController(RegistrationRequestService rrs) {
		this.rrs = rrs;
	}
	
	@PostMapping("/registerPatient")
	public int addPatient(@RequestBody RegistrationRequestDTO patient) {
		RegistrationRequest rr = new RegistrationRequest();
		rr.setAddress(patient.getAddress());
		rr.setCity(patient.getCity());
		rr.setCountry(patient.getCountry());
		rr.setEmail(patient.getEmail());
		rr.setInsuranceId(patient.getInsuranceId());
		rr.setName(patient.getName());
		rr.setNumber(patient.getNumber());
		rr.setPassword(patient.getPassword());
		rr.setSurname(patient.getSurname());
		rrs.addRegistrationRequest(rr);
		return 1;
	}

}
