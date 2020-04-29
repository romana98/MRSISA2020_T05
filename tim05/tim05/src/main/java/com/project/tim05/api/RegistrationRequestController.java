package com.project.tim05.api;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.validator.cfg.defs.EmailDef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.tim05.dto.RegistrationRequestDTO;
import com.project.tim05.model.RegistrationRequest;
import com.project.tim05.service.EmailService;
import com.project.tim05.service.RegistrationRequestService;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/registrationRequests")
@RestController
public class RegistrationRequestController<T> {
	
	private final RegistrationRequestService rrs;
	private final EmailService es;
	
	@Autowired
	public RegistrationRequestController(RegistrationRequestService rrs, EmailService es) {
		this.rrs = rrs;
		this.es = es;
	}
	
	@GetMapping("/getRequests")
	public List<RegistrationRequestDTO> getRequests(){
		List<RegistrationRequestDTO> rrDTO = new ArrayList<RegistrationRequestDTO>();
		List<RegistrationRequest> regs = rrs.getRequests();
		for (RegistrationRequest rr : regs) {
			rrDTO.add(new RegistrationRequestDTO(rr.getEmail(), rr.getPassword(), rr.getName(), rr.getSurname(), rr.getAddress(), rr.getCity(), rr.getCountry(), rr.getPhone_number(), rr.getInsurance_number()));
		}
		return rrDTO;
	}
	
	@PostMapping("/registerPatient")
	public ResponseEntity<T> addPatient(@Valid @RequestBody RegistrationRequestDTO patient) {
	
		RegistrationRequest rr = new RegistrationRequest();
		rr.setAddress(patient.getAddress());
		rr.setCity(patient.getCity());
		rr.setCountry(patient.getCountry());
		rr.setEmail(patient.getEmail());
		rr.setInsurance_number(patient.getInsurance_number());
		rr.setName(patient.getName());
		rr.setPhone_number(patient.getPhone_number());
		rr.setPassword(patient.getPassword());
		rr.setSurname(patient.getSurname());
		
		int flag = rrs.addRegistrationRequest(rr);
		if(flag == 0)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		else
			return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
	@PostMapping("/declineRequest")
	public ResponseEntity<T> declineRequest(@Valid @RequestBody RegistrationRequestDTO patient, String text) {
		RegistrationRequest rr = new RegistrationRequest();
		rr.setAddress(patient.getAddress());
		rr.setCity(patient.getCity());
		rr.setCountry(patient.getCountry());
		rr.setEmail(patient.getEmail());
		rr.setInsurance_number(patient.getInsurance_number());
		rr.setName(patient.getName());
		rr.setPhone_number(patient.getPhone_number());
		rr.setPassword(patient.getPassword());
		rr.setSurname(patient.getSurname());
		int flag = rrs.removeRegistrationRequest(rr);
		
		if(flag == 0)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		else
			return ResponseEntity.status(HttpStatus.OK).body(null);
		
	}

}
