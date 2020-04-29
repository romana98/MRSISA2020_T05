package com.project.tim05.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.tim05.dto.MedicalStaffDTO;
import com.project.tim05.model.Doctor;
import com.project.tim05.model.Nurse;
import com.project.tim05.service.DoctorService;
import com.project.tim05.service.NurseService;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/medicalStaff")
@RestController
public class MedicalStaffController<T> {
	
	private final DoctorService ds;
	private final NurseService ns;
	
	@Autowired
	public MedicalStaffController(DoctorService ds, NurseService ns) {
		this.ds = ds;
		this.ns = ns;
	}
	
	@PostMapping("/editMedicalStaff")
	public ResponseEntity<T> editProfile(@Valid @RequestBody MedicalStaffDTO ms) {
		
		if(ms.getType().equals("doctor")) {
			Doctor d = new Doctor();
			List<Doctor> list = ds.getDoctors();
			for(Doctor d2 : list) {
				if(d2.getEmail().equals(ms.getEmail())) {
					d = d2;
					d.setName(ms.getName());
					d.setPassword(ms.getPassword());
					d.setSurname(ms.getSurname());
					break;
				}
			}
			ds.editProfile(d);
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}else {
			Nurse d = new Nurse();
			List<Nurse> list = ns.getNurses();
			for(Nurse d2 : list) {
				if(d2.getEmail().equals(ms.getEmail())) {
					d = d2;
					d.setName(ms.getName());
					d.setPassword(ms.getPassword());
					d.setSurname(ms.getSurname());
					break;
				}
			}
			ns.editProfile(d);
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}
		
	}
	
	

}
