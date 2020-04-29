package com.project.tim05.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.tim05.dto.AppointmentDTO;
import com.project.tim05.model.Appointment;
import com.project.tim05.model.AppointmentType;
import com.project.tim05.model.Doctor;
import com.project.tim05.model.Hall;
import com.project.tim05.service.AppointmentService;
import com.project.tim05.service.AppointmentTypeService;
import com.project.tim05.service.DoctorService;
import com.project.tim05.service.HallService;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/appointment")
@RestController
public class AppointmentController {

	private final AppointmentService as;
	private final DoctorService ds;
	private final HallService hs;
	private final AppointmentTypeService ats;

	@Autowired
	public AppointmentController(AppointmentService as, DoctorService ds, HallService hs, AppointmentTypeService ats) {
		super();
		this.as = as;
		this.ds = ds;
		this.hs = hs;
		this.ats = ats;
	}

	@PostMapping("/addAppointment")
	public ResponseEntity<String> addAppointment(@RequestBody AppointmentDTO adto) {
		Appointment ap = new Appointment();
		
		Doctor dr = ds.getDoctorbyID(adto.getDoctor_id());
		Hall hall = hs.getHallbyId(adto.getHall_id());
		AppointmentType at = ats.getAppointmentTypebyId(adto.getAppointmentType_id());
		
		if (dr == null || hall == null || at == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
		ap.setDateTime(adto.getDateTime());
		ap.setDuration(adto.getDuration());
		ap.setPrice(adto.getPrice());
		ap.setRequest(adto.isRequest());
		ap.setPredefined(adto.isPredefined());
		ap.setDoctor(dr);
		ap.setAppointmentType(at);
		ap.setHall(hall);
		
		int flag = as.addAppointment(ap);
		
		if(flag == 0)
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		else
			return ResponseEntity.status(HttpStatus.OK).body(null);
		
		
	}

}
