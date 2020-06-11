package com.project.tim05.api;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.tim05.dto.ClinicDTO;
import com.project.tim05.model.Clinic;
import com.project.tim05.model.ClinicAdministrator;
import com.project.tim05.service.ClinicAdministratorService;
import com.project.tim05.service.ClinicService;


@CrossOrigin(origins = "https://localhost:4200")

@RequestMapping("/clinic")
@RestController
public class ClinicController<T> {

	private final ClinicService cs;
	private final ClinicAdministratorService cas;
	
	@Autowired
	public ClinicController(ClinicService cs, ClinicAdministratorService cas) {
		this.cs = cs;
		this.cas = cas;
	}
	
	@GetMapping("/getClinics")
	@PreAuthorize("hasRole('CLINIC_CENTER_ADMIN')")
	public List<ClinicDTO> getClinics(){
		List<ClinicDTO> clsDTO = new ArrayList<ClinicDTO>();
		List<Clinic> cls = cs.getClinics();
		for (Clinic clinic : cls) {
			clsDTO.add(new ClinicDTO(clinic.getName(), clinic.getAddress(), clinic.getDescription()));
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
	
	@PostMapping("/editClinic")
	@PreAuthorize("hasRole('CLINIC_ADMIN')")
	public ResponseEntity<T> editClinic(@Valid @RequestBody ClinicDTO c) {
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		ClinicAdministrator ca = (ClinicAdministrator) currentUser.getPrincipal();
		String email = ca.getEmail();

		ClinicAdministrator cad = cas.getClinicAdmin(email);
		
		int flag = cs.editClinic(new Clinic(cad.getClinic().getId(), c.getName(), c.getAddress(), c.getDescription()));
		
		if(flag == 0)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		else
			return ResponseEntity.status(HttpStatus.OK).body(null);
	
	}
	 
	
	
	
}
