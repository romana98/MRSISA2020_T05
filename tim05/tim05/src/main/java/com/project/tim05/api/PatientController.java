package com.project.tim05.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public int addPatient(@RequestBody PatientDTO p) {
		ps.addPatient(new Patient(p.getEmail(), p.getPassword(), p.getName(), p.getSurname(), p.getAddress(), p.getCity(), p.getCountry(), p.getPhone_number(), p.getInsurance_number()));
		rrs.removeRegistrationRequest(new RegistrationRequest(p.getEmail(), p.getPassword(), p.getName(), p.getSurname(), p.getAddress(), p.getCity(), p.getCountry(), p.getPhone_number(), p.getInsurance_number()));
		
		return 200;
	}
	
}
