package com.project.tim05.api;

import java.util.ArrayList;
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

import com.project.tim05.dto.ClinicDTO;
import com.project.tim05.model.Clinic;
import com.project.tim05.service.ClinicService;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/clinic")
@RestController
public class ClinicController<T> {

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
	public ResponseEntity<T> addClinic(@RequestBody ClinicDTO c) {
		if(check(c) == 0)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		
		Clinic cl = new Clinic(c.getName(), c.getAddress(), c.getDescription());
		int flag = cs.addClinic(cl);
		System.out.println(flag);
		if(flag == 0)
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		else
			return ResponseEntity.status(HttpStatus.OK).body(null);
	
	}
	
	public int check(ClinicDTO cDTO) {   
		
		if(cDTO.getName() == null || cDTO.getName().length() == 0 || cDTO.getName().matches("[^A-Za-z0-9 ]*")) {
			return 0;
		}
		else if(cDTO.getAddress() == null || cDTO.getAddress().length() == 0) {
			return 0;
		}
		else if(cDTO.getDescription() == null || cDTO.getDescription().length() == 0) {
			return 0;
		}
		
		return 1;
		
	}
}
