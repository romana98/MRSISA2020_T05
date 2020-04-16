package com.project.tim05.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.tim05.model.Patient;
import com.project.tim05.service.PatientService;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/patients")
@RestController
public class PatientController {
	
	private final PatientService ps;
	
	@Autowired
	public PatientController(PatientService ps) {
		this.ps = ps;
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
	
	@PostMapping("/registerPatient")
	public int addPatient(@RequestBody Patient patient) {
		ps.addPatient(patient);
		return 1;
	}
	
}
