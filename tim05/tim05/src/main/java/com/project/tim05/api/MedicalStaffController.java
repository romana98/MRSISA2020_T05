package com.project.tim05.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.tim05.dto.MedicalStaffDTO;
import com.project.tim05.model.Authority;
import com.project.tim05.model.Doctor;
import com.project.tim05.model.MedicalStaff;
import com.project.tim05.model.Nurse;
import com.project.tim05.service.CustomUserDetailsService;
import com.project.tim05.service.DoctorService;
import com.project.tim05.service.NurseService;
import com.project.tim05.service.UserService;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/medicalStaff")
@RestController
public class MedicalStaffController<T> {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationController ac;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	private final DoctorService ds;
	private final NurseService ns;
	
	@Autowired
	public MedicalStaffController(DoctorService ds, NurseService ns) {
		this.ds = ds;
		this.ns = ns;
	}
	
	@PostMapping("/editMedicalStaff")
	@PreAuthorize("hasRole('DOCTOR') || hasRole('NURSE')") 
	public ResponseEntity<T> editProfile(@Valid @RequestBody MedicalStaffDTO ms) {
		
		Authentication current = SecurityContextHolder.getContext().getAuthentication();
		MedicalStaff currentUser = (MedicalStaff)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String email = currentUser.getEmail();
		if(!email.equals(ms.getEmail()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

		if(currentUser.getClass() == Doctor.class) {
			Doctor d = new Doctor();
			d.setPassword(ms.getPassword());
			d.setName(ms.getName());
			d.setSurname(ms.getSurname());
			d.setEmail(ms.getEmail());
			int flag = ds.editProfile(d);
			if(flag == 0) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
			}else {
				Authentication authentication = authenticationManager
						.authenticate(new UsernamePasswordAuthenticationToken(currentUser.getEmail(),ms.getPassword()));

				SecurityContextHolder.getContext().setAuthentication(authentication);
				return ResponseEntity.status(HttpStatus.OK).body(null);
			}
		}else {
			Nurse d = new Nurse();
			d.setPassword(ms.getPassword());
			d.setName(ms.getName());
			d.setSurname(ms.getSurname());
			d.setEmail(ms.getEmail());
			int flag = ns.editProfile(d);
			if(flag == 0) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
			}else {
				Authentication authentication = authenticationManager
						.authenticate(new UsernamePasswordAuthenticationToken(currentUser.getEmail(),ms.getPassword()));

				SecurityContextHolder.getContext().setAuthentication(authentication);
				return ResponseEntity.status(HttpStatus.OK).body(null);
			}
		}
		
	}
	
	

}
