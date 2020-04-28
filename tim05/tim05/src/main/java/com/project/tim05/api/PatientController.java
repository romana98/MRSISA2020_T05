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

import com.project.tim05.dto.PatientDTO;
import com.project.tim05.model.Patient;
import com.project.tim05.model.RegistrationRequest;
import com.project.tim05.service.PatientService;
import com.project.tim05.service.RegistrationRequestService;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/patients")
@RestController
public class PatientController {
	
	private final PatientService ps;
	private final RegistrationRequestService rrs;
	
	@Autowired
	public PatientController(PatientService ps, RegistrationRequestService rrs) {
		this.ps = ps;
		this.rrs = rrs;
	}
	
	@GetMapping("/getPatients")
	public List<Patient> getPatients(){
		return ps.getPatients();
	}
	
	@PostMapping("/editPatient")
	public int editPatient(@RequestBody Patient patient) {
		ps.editPatient(patient);
		return 0;
	}
	
	@PostMapping("/addPatient")
	public <T> ResponseEntity<T> addPatient(@RequestBody PatientDTO p) {
		//TODO check
		
		int flag = ps.addPatient(new Patient(p.getEmail(), p.getPassword(), p.getName(), p.getSurname(), p.getAddress(), p.getCity(), p.getCountry(), p.getPhone_number(), p.getInsurance_number()));
		int flag1 = rrs.removeRegistrationRequest(new RegistrationRequest(p.getEmail(), p.getPassword(), p.getName(), p.getSurname(), p.getAddress(), p.getCity(), p.getCountry(), p.getPhone_number(), p.getInsurance_number()));
		
		
		if(flag1 == 0)
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		else if(flag == 0)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		else
			return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
}
