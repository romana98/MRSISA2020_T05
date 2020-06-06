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

import com.project.tim05.dto.LeaveRequestDTO;
import com.project.tim05.model.Doctor;
import com.project.tim05.model.LeaveRequest;
import com.project.tim05.model.MedicalStaff;
import com.project.tim05.model.Nurse;
import com.project.tim05.model.User;
import com.project.tim05.service.DoctorService;
import com.project.tim05.service.EmailService;
import com.project.tim05.service.LeaveRequestService;
import com.project.tim05.service.NurseService;
import com.project.tim05.service.UserService;

@CrossOrigin(origins = "https://localhost:4200")
@RequestMapping("/leaveRequest")
@RestController
public class LeaveRequestController<T> {
	
	@Autowired
	private UserService userService;
	
	private final LeaveRequestService lrs;

	private final DoctorService ds;

	private final NurseService ns;
	
	@Autowired
	private EmailService es;

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
	
	@GetMapping("/getRequests")
	@PreAuthorize("hasRole('CLINIC_CENTER_ADMIN')")
	public List<LeaveRequestDTO> getRequests(){
		List<LeaveRequestDTO> lrDTO = new ArrayList<LeaveRequestDTO>();
		List<LeaveRequest> regs = lrs.getRequests();
		for (LeaveRequest lr : regs) {
			lrDTO.add(new LeaveRequestDTO(lr.getEmail(), lr.getName(), lr.getSurname(), lr.getStartDate(), lr.getEndDate()));
		}
		return lrDTO;
	}
	
	@PostMapping("/declineRequest")
	@PreAuthorize("hasRole('CLINIC_CENTER_ADMIN')")
	public ResponseEntity<T> declineRequest(@Valid @RequestBody LeaveRequestDTO l) {
		User existUser = this.userService.findByEmail(l.getEmail());
		if (existUser != null) {
			 ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		}
		
		
		LeaveRequest lr = new LeaveRequest(l.getStartDate(), l.getEndDate(),l.getEmail(), l.getName(), l.getSurname());
		int flag = lrs.removeLeaveRequest(lr);
		
		if(flag == 0)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		else
		{
			try {
				es.sendDeclinanceMail(l.getEmail(), l.getText());
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			
			}
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}
		
			
		
	}
	
	
	
	

}
