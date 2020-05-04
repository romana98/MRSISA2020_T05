package com.project.tim05.api;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.project.tim05.dto.RegistrationRequestDTO;
import com.project.tim05.model.RegistrationRequest;
import com.project.tim05.model.User;
import com.project.tim05.service.EmailService;
import com.project.tim05.service.RegistrationRequestService;
import com.project.tim05.service.UserService;

@RequestMapping("/registrationRequests")
@RestController
public class RegistrationRequestController<T> {

	@Autowired
	private UserService userService;
	
	private final RegistrationRequestService rrs;
	
	private final EmailService es;
	
	@Autowired
	public RegistrationRequestController(RegistrationRequestService rrs, EmailService es) {
		this.rrs = rrs;
		this.es = es;
	}
	
	@GetMapping("/getRequests")
	@PreAuthorize("hasRole('CLINIC_CENTER_ADMIN')")
	public List<RegistrationRequestDTO> getRequests(){
		List<RegistrationRequestDTO> rrDTO = new ArrayList<RegistrationRequestDTO>();
		List<RegistrationRequest> regs = rrs.getRequests();
		for (RegistrationRequest rr : regs) {
			rrDTO.add(new RegistrationRequestDTO(rr.getEmail(), rr.getPassword(), rr.getName(), rr.getSurname(), rr.getAddress(), rr.getCity(), rr.getCountry(), rr.getPhone_number(), rr.getInsurance_number()));
		}
		return rrDTO;
	}
	
	@PostMapping("/registerPatient")
	public ResponseEntity<T> addPatient(@Valid @RequestBody RegistrationRequestDTO patient, UriComponentsBuilder ucBuilder) throws Exception {
	
		User existUser = this.userService.findByEmail(patient.getEmail());
		if (existUser != null) {
			 ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		}
		
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
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		else
			return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
	
	
	@PostMapping("/declineRequest")
	@PreAuthorize("hasRole('CLINIC_CENTER_ADMIN')")
	public ResponseEntity<T> declineRequest(@Valid @RequestBody RegistrationRequestDTO patient) {
		User existUser = this.userService.findByEmail(patient.getEmail());
		if (existUser != null) {
			 ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		}
		
		
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
		{
			try {
				es.sendDeclinanceMail(patient.getEmail(), patient.getText());
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			
			}
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}
		
			
		
	}

}
