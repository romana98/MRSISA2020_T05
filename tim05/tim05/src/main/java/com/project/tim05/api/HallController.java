package com.project.tim05.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.tim05.dto.HallDTO;
import com.project.tim05.model.Clinic;
import com.project.tim05.model.ClinicAdministrator;
import com.project.tim05.model.Hall;
import com.project.tim05.model.User;
import com.project.tim05.service.ClinicAdministratorService;
import com.project.tim05.service.HallService;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/halls")
@RestController
public class HallController<T> {
	
	private final HallService hs;
	private final ClinicAdministratorService cas;
	
	@Autowired
	public HallController(HallService hs, ClinicAdministratorService cas) {
		this.hs = hs;
		this.cas = cas;
	}
	
	@PostMapping("/addHall")
	@PreAuthorize("hasRole('CLINIC_ADMIN')")
	public ResponseEntity<T> addHall(@Valid @RequestBody HallDTO hall) {
		Hall h = new Hall();
		h.setName(hall.getName());
		h.setNumber(hall.getNumber());
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		ClinicAdministrator currentUser = (ClinicAdministrator) authentication.getPrincipal();
		h.setClinicAdmin(currentUser);
		h.setClinic(currentUser.getClinic());
		
		int flag = hs.addHall(h);
		
		if(flag == 0)
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		else
			return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
	@GetMapping("/getClinicHall")
	private ResponseEntity<List<Hall>> getHalls(@RequestParam String clinic_id){
		return ResponseEntity.ok(hs.getClinicHalls(Integer.parseInt(clinic_id)));
	}
	
	@DeleteMapping("/deleteHall")
	private ResponseEntity<T> deleteHall(@RequestParam String hall_id){
		int flag = hs.deleteHall(Integer.parseInt(hall_id));
		if(flag != 0) {
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}
		else {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		}

		
	}


}
