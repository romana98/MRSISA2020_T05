package com.project.tim05.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.tim05.dto.ClinicDTO;
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
	public List<ClinicDTO> getClinics(){
		List<ClinicDTO> clsDTO = new ArrayList<ClinicDTO>();
		List<Clinic> cls = cs.getClinics();
		for (Clinic clinic : cls) {
			clsDTO.add(new ClinicDTO(clinic.getName(), clinic.getAddress(), clinic.getDescription()));
		}
		return clsDTO;
	}
	
	@PostMapping("/addClinic")
	public int addClinic(@RequestBody ClinicDTO c) {
		Clinic cl = new Clinic(c.getName(), c.getAddress(), c.getDescription());
		cs.addClinic(cl);
		return 200;
	}
}
