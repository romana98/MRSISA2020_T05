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

import com.project.tim05.dto.NurseDTO;
import com.project.tim05.dto.PatientDTO;
import com.project.tim05.model.Clinic;
import com.project.tim05.model.Nurse;
import com.project.tim05.model.Patient;
import com.project.tim05.model.User;
import com.project.tim05.service.ClinicService;
import com.project.tim05.service.NurseService;
import com.project.tim05.service.PatientService;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/nurse")
@RestController
public class NurseController {
	
	private final PatientService ps;
	private final NurseService ns;
	private final ClinicService cs;
	
	@Autowired
	public NurseController(PatientService ps, NurseService ns, ClinicService cs) {
		this.ps = ps;
		this.ns = ns;
		this.cs = cs;
	}
	
	@GetMapping("/getPatients")
	@PreAuthorize("hasRole('NURSE')")
	public List<PatientDTO> getPatients(){
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (Nurse) authentication.getPrincipal();
		
		List<Patient> pss = ps.getPatients(((Nurse) user).getClinic());
		
		List<PatientDTO> psDTO = new ArrayList<PatientDTO>();
		for (Patient p : pss) {
			psDTO.add(new PatientDTO(p.getEmail(), p.getName(), p.getSurname(), p.getAddress(), p.getCity(), p.getCountry(), p.getPhone_number(), p.getInsurance_number()));
		}
		return psDTO;
	}
	
	@PostMapping("/addNurse")
	public ResponseEntity<Object> addNurse(@Valid @RequestBody NurseDTO nurse) {
		Nurse n = new Nurse();
				
		Clinic c = cs.getClinicbyId(nurse.getClinic_id());
		
		if (c == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
		
		n.setClinic(c);
		n.setName(nurse.getName());
		n.setSurname(nurse.getSurname());
		n.setEmail(nurse.getEmail());
		n.setPassword(nurse.getPassword());
		n.setWorkStart(nurse.getWorkStart());
		n.setWorkEnd(nurse.getWorkEnd());
		
		
		int flag = ns.addNurse(n);
		
		System.out.println(flag);
		
		if(flag != 0) {
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}else {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		}
	
		
	}

}
