package com.project.tim05.api;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.tim05.model.Doctor;
import com.project.tim05.service.DoctorService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/doctors")
@RestController
public class DoctorController {

	private final DoctorService ds;
	
	@Autowired
	public DoctorController(DoctorService ds) {
		this.ds = ds;
	}
	
	@GetMapping("/getDoctors")
	public List<Doctor> getHelloWorld(){
		return ds.getDoctors();
	}
	
	@PostMapping("/addDoctor")
	public int addDoctor(@RequestBody Doctor doctor) {
		ds.addDoctor(doctor);
		return 1;
	}
	
	
}
