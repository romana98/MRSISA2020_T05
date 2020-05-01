package com.project.tim05.api;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public List<PatientDTO> getPatients(){
		List<PatientDTO> psDTO = new ArrayList<PatientDTO>();
		psDTO.add(new PatientDTO("email", "pass", "name", "surname", "address", "city", "town", "phone_number", "insurance_number"));
		return psDTO;
	}
	
	@PostMapping("/addNurse")
	public ResponseEntity<Object> addNurse(@Valid @RequestBody NurseDTO nurse) {
		Nurse n = new Nurse();
				
		Clinic c = cs.getClinicbyId(nurse.getClinic_id());
		
		
		n.setClinic(c);
		n.setName(nurse.getName());
		n.setSurname(nurse.getSurname());
		n.setEmail(nurse.getEmail());
		n.setPassword(nurse.getPassword());
		n.setWorkStart(nurse.getWorkStart());
		n.setWorkEnd(nurse.getWorkEnd());
		
		
		int flag = ns.addNurse(n);
		
		if(flag == 0)
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		else
			return ResponseEntity.status(HttpStatus.OK).body(null);
	
		
	}

}
