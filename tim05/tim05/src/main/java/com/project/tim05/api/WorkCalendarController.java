package com.project.tim05.api;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.tim05.dto.AppointmentDTO;
import com.project.tim05.dto.WorkCalendarDTO;
import com.project.tim05.model.Doctor;
import com.project.tim05.model.MedicalStaff;
import com.project.tim05.model.Nurse;
import com.project.tim05.service.DoctorService;
import com.project.tim05.service.NurseService;
import com.project.tim05.service.WorkCalendarService;

//@CrossOrigin(origins = "https://localhost:4200")
@CrossOrigin(origins = "https://eclinic05.herokuapp.com")
@RestController
@RequestMapping(value = "/workCalendar")
public class WorkCalendarController {
	
	private final WorkCalendarService wcs;
	private final DoctorService ds;
	private final NurseService ns;
	
	public WorkCalendarController(WorkCalendarService wcs, DoctorService ds, NurseService ns) {
		this.wcs = wcs;
		this.ds = ds;
		this.ns = ns;
	}
	
	@GetMapping("/getWorkCalendar")
	@PreAuthorize("hasRole('DOCTOR') || hasRole('NURSE')")
	public List<WorkCalendarDTO> getWorkCalendar() {
		
		Authentication current = SecurityContextHolder.getContext().getAuthentication();
		MedicalStaff currentUser = (MedicalStaff)current.getPrincipal();
		
		Doctor d = ds.getDoctor(currentUser.getEmail());
		Nurse n = ns.getNurse(currentUser.getEmail());
		
		List<WorkCalendarDTO> wc = null;
		if(d != null) {

		 wc = wcs.getWorkCalendar(d.getEmail());
		}
		else
		{
			wc = wcs.getWorkCalendar(n.getEmail());
		}
	
		return wc;
	}

}
