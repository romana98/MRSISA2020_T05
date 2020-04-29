package com.project.tim05.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
	public void addAppointmentType(@Valid @RequestBody AppointmentTypeDTO atDTO) {
		AppointmentType at = new AppointmentType(atDTO.getName());
		
		ats.addAppointmentType(at);
		
	}
	
	@GetMapping("/getAppointmentTypes")
	public List<AppointmentType> getAppointmetTypes(){
		return ats.getAppointmentTypes();
	}
	
	
	
}
