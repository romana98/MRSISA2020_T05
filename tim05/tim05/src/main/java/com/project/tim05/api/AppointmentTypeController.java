package com.project.tim05.api;

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

import com.project.tim05.dto.AppointmentTypeDTO;
import com.project.tim05.model.AppointmentType;
import com.project.tim05.service.AppointmentTypeService;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/appointmentType")
@RestController
public class AppointmentTypeController {

	private final AppointmentTypeService ats;

	@Autowired
	public AppointmentTypeController(AppointmentTypeService ats) {
		super();
		this.ats = ats;
	}
	
	@PostMapping("/addAppointmentType")
	@PreAuthorize("hasRole('CLINIC_ADMIN')")
	public ResponseEntity<Object> addAppointmentType(@Valid @RequestBody AppointmentTypeDTO atDTO) {
		AppointmentType at = new AppointmentType(atDTO.getName());
		
		int flag = ats.addAppointmentType(at);
		
		if(flag == 0)
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		else
			return ResponseEntity.status(HttpStatus.OK).body(null);
		
	}
	
	@GetMapping("/getAppointmentTypes")
	@PreAuthorize("hasRole('CLINIC_ADMIN') || hasRole('PATIENT')")
	public List<AppointmentType> getAppointmetTypes(){
		return ats.getAppointmentTypes();
	}
	
	
	
}
