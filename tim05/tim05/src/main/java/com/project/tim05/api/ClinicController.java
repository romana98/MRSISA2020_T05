package com.project.tim05.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.tim05.model.Clinic;
import com.project.tim05.service.ClinicService;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/clinic")
@RestController
public class ClinicController {

	private final ClinicService cs;
	
	@Autowired
	public ClinicController(ClinicService cs) {
		this.cs = cs;
	}
	
	@GetMapping("/getClinics")
	public List<Clinic> getClinics(){
		return cs.getClinic();
	}
	
	@PostMapping("/addClinic")
	public int addDoctor(@RequestBody Clinic c) {
		cs.addClinic(c);
		return 200;
	}
}
