package com.project.tim05.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.tim05.dto.PatientDTO;
import com.project.tim05.service.NurseService;
import com.project.tim05.service.PatientService;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/nurse")
@RestController
public class NurseController {
	
	private final PatientService ps;
	private final NurseService ns;
	
	@Autowired
	public NurseController(PatientService ps, NurseService ns) {
		this.ps = ps;
		this.ns = ns;
	}
	
	@GetMapping("/getPatients")
	public List<PatientDTO> getPatients(){
		List<PatientDTO> psDTO = new ArrayList<PatientDTO>();
		psDTO.add(new PatientDTO("email", "pass", "name", "surname", "address", "city", "town", "phone_number", "insurance_number"));
		return psDTO;
	}

}
