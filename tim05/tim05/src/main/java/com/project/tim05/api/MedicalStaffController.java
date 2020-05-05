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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.tim05.dto.DoctorDTO;
import com.project.tim05.dto.MedicalStaffDTO;
import com.project.tim05.dto.NurseDTO;
import com.project.tim05.model.Doctor;
import com.project.tim05.model.MedicalStaff;
import com.project.tim05.model.Nurse;
import com.project.tim05.model.User;
import com.project.tim05.service.DoctorService;
import com.project.tim05.service.NurseService;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/medicalStaff")
@RestController
public class MedicalStaffController<T> {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	private final DoctorService ds;
	private final NurseService ns;
	
	@Autowired
	public MedicalStaffController(DoctorService ds, NurseService ns) {
		this.ds = ds;
		this.ns = ns;
	}
	
	
	@GetMapping("/getData")
	@PreAuthorize("hasRole('DOCTOR') || hasRole('NURSE')")
	public MedicalStaffDTO getData() {
		
		
		Authentication current = SecurityContextHolder.getContext().getAuthentication();
		User currentUser = (User)current.getPrincipal();
		
		Doctor d = ds.getDoctor(currentUser.getEmail());
		Nurse n = ns.getNurse(currentUser.getEmail());
		
		if(d != null) {
			DoctorDTO ddto = new DoctorDTO();
			ddto.setEmail(d.getEmail());
			ddto.setName(d.getName());
			ddto.setPassword(d.getPassword());
			ddto.setSurname(d.getSurname());
			ddto.setType("doctor");
			ddto.setWorkEnd(d.getWorkEnd());
			ddto.setWorkStart(d.getWorkStart());
			return ddto;
		}else {
			NurseDTO ddto = new NurseDTO();
			ddto.setEmail(n.getEmail());
			ddto.setName(n.getName());
			ddto.setPassword(n.getPassword());
			ddto.setSurname(n.getSurname());
			ddto.setType("nurse");
			ddto.setWorkEnd(n.getWorkEnd());
			ddto.setWorkStart(n.getWorkEnd());
			return ddto;
		}
		
		
	}
	
	@PostMapping("/editMedicalStaff")
	@PreAuthorize("hasRole('DOCTOR') || hasRole('NURSE')") 
	public ResponseEntity<T> editProfile(@Valid @RequestBody MedicalStaffDTO ms) {
		
		User currentUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String email = currentUser.getEmail();
		if(!email.equals(ms.getEmail()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

		if(ms.getPassword().length() > 0 && ms.getPassword().length() < 8) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
		if(ms.getType().equals("doctor")) {
			Doctor d = new Doctor();
			d.setPassword(ms.getPassword());
			d.setName(ms.getName());
			d.setSurname(ms.getSurname());
			d.setEmail(ms.getEmail());
			d.setId(currentUser.getId());
			int flag = ds.editProfile(d);
			
			if(ms.getPassword().length() != 0 && flag != 0)
			{
				Authentication authentication = authenticationManager
						.authenticate(new UsernamePasswordAuthenticationToken(d.getEmail(),ms.getPassword()));

				SecurityContextHolder.getContext().setAuthentication(authentication);
					
			}
			
			if(flag == 0) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
			}else {
				return ResponseEntity.status(HttpStatus.OK).body(null);
			}
		}else {
			Nurse d = new Nurse();
			d.setPassword(ms.getPassword());
			d.setName(ms.getName());
			d.setSurname(ms.getSurname());
			d.setEmail(ms.getEmail());
			d.setId(currentUser.getId());
			int flag = ns.editProfile(d);
			
			if(ms.getPassword().length() != 0 && flag != 0)
			{
				Authentication authentication = authenticationManager
						.authenticate(new UsernamePasswordAuthenticationToken(d.getEmail(),ms.getPassword()));

				SecurityContextHolder.getContext().setAuthentication(authentication);
					
			}
			
			if(flag == 0) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
			}else {
				return ResponseEntity.status(HttpStatus.OK).body(null);
			}
		}
		
	}
	
	

}
