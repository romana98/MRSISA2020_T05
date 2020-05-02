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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.tim05.dto.DoctorDTO;
import com.project.tim05.model.AppointmentType;
import com.project.tim05.model.Clinic;
import com.project.tim05.model.Doctor;
import com.project.tim05.service.AppointmentTypeService;
import com.project.tim05.service.ClinicService;
import com.project.tim05.service.DoctorService;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/doctors")
@RestController
public class DoctorController {

	private final DoctorService ds;
	private final AppointmentTypeService ats;
	private final ClinicService cs;
	
	@Autowired
	public DoctorController(DoctorService ds, AppointmentTypeService ats, ClinicService cs) {
		this.ds = ds;
		this.ats = ats;
		this.cs = cs;
	}
	
	@GetMapping("/getDoctors")
	public List<Doctor> getHelloWorld(){
		return ds.getDoctors();
	}
	
	@PostMapping("/addDoctor")
	public ResponseEntity<Object> addDoctor(@Valid @RequestBody DoctorDTO doctor) {
		Doctor dr = new Doctor();
		
		AppointmentType at = ats.getAppointmentTypebyId(doctor.getAppointment_type_id());
		
		Clinic c = cs.getClinicbyId(doctor.getClinic_id());
		
		if (at == null || c == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
		dr.setClinic(c);
		dr.setAppointmentType(at);
		dr.setName(doctor.getName());
		dr.setSurname(doctor.getSurname());
		dr.setEmail(doctor.getEmail());
		dr.setPassword(doctor.getPassword());
		dr.setWorkStart(doctor.getWorkStart());
		dr.setWorkEnd(doctor.getWorkEnd());
		
		int flag = ds.addDoctor(dr);
		
		if(flag == 0)
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		else
			return ResponseEntity.status(HttpStatus.OK).body(null);
	
		
	}
	
	@GetMapping("/getDoctorsAppointment")
	public ResponseEntity<ArrayList<Doctor>> getDoctors(@RequestParam String clinic_id,String appointment_type_id){
		return ResponseEntity.ok(ds.getDoctors(Integer.parseInt(clinic_id),Integer.parseInt(appointment_type_id)));
	}
	
}
