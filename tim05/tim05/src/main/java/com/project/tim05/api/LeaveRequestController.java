package com.project.tim05.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.tim05.dto.LeaveRequestDTO;
import com.project.tim05.model.Doctor;
import com.project.tim05.model.MedicalStaff;
import com.project.tim05.model.Nurse;
import com.project.tim05.service.DoctorService;
import com.project.tim05.service.LeaveRequestService;
import com.project.tim05.service.NurseService;

@CrossOrigin(origins = "https://localhost:4200")
@RequestMapping("/leaveRequest")
@RestController
public class LeaveRequestController<T> {
	
	@Autowired
	private final LeaveRequestService lrs;
	@Autowired
	private final DoctorService ds;
	@Autowired
	private final NurseService ns;

	public LeaveRequestController(LeaveRequestService lrs, DoctorService ds, NurseService ns) {
		this.lrs = lrs;
		this.ds = ds;
		this.ns = ns;
	}
	
	@PostMapping("/makeLeaveRequest")
	@PreAuthorize("hasRole('DOCTOR') || hasRole('NURSE')")
	public ResponseEntity<T> makeLeaveRequest(@Valid @RequestBody LeaveRequestDTO lrDTO)
	{
		
		Authentication current = SecurityContextHolder.getContext().getAuthentication();
		MedicalStaff currentUser = (MedicalStaff)current.getPrincipal();
		lrDTO.setEmail(currentUser.getEmail());
		
		Doctor d = ds.getDoctor(currentUser.getEmail());
		Nurse n = ns.getNurse(currentUser.getEmail());
		
		if(d != null) {
			lrDTO.setName(d.getName());
			lrDTO.setSurname(d.getSurname());
			
		}else {
			lrDTO.setName(n.getName());
			lrDTO.setSurname(n.getSurname());
		}
			
		int flag = lrs.addLeaveRequest(lrDTO);
		
		if(flag == 0) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}
	}
	
	
	
	

}
