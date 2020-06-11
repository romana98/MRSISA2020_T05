package com.project.tim05.api;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.tim05.dto.ClinicDTO;
import com.project.tim05.model.Clinic;
import com.project.tim05.service.ClinicService;


@CrossOrigin(origins = "https://localhost:4200")

@RequestMapping("/clinic")
@RestController
public class ClinicController<T> {

	private final ClinicService cs;
	
	@Autowired
	public ClinicController(ClinicService cs) {
		this.cs = cs;
	}
	
	@GetMapping("/getClinics")
	@PreAuthorize("hasRole('CLINIC_CENTER_ADMIN') || hasRole('PATIENT')")
	public List<ClinicDTO> getClinics(){
		List<ClinicDTO> clsDTO = new ArrayList<ClinicDTO>();
		List<Clinic> cls = cs.getClinics();
		for (Clinic clinic : cls) {
			ClinicDTO dto = new ClinicDTO();
			dto.setName(clinic.getName());
			dto.setAddress(clinic.getAddress());
			double avg = 0.0;
			double zbir = 0.0;
			for(double d : clinic.getRatings()) {
				zbir += d;
			}
			avg = zbir/clinic.getRatings().size();
			dto.setAvg_rating(avg);
			dto.setDescription(clinic.getDescription());
			dto.setId(clinic.getId());
			clsDTO.add(dto);
		}
		return clsDTO;
	}
	
	@PostMapping("/addClinic")
	@PreAuthorize("hasRole('CLINIC_CENTER_ADMIN')")
	public ResponseEntity<T> addClinic(@Valid @RequestBody ClinicDTO c) {
		
		int flag = cs.addClinic(new Clinic(c.getName(), c.getAddress(), c.getDescription()));
		
		if(flag == 0)
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		else
			return ResponseEntity.status(HttpStatus.OK).body(null);
	
	}
	 
	
	
	
}
