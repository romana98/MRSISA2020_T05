package com.project.tim05.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.tim05.model.AdminClinic;
import com.project.tim05.service.AdminClinicService;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/adminClinic")
@RestController
public class AdminClinicController {

	private final AdminClinicService acs;
	
	@Autowired
	public AdminClinicController(AdminClinicService acs) {
		this.acs = acs;
	}
	
	@GetMapping("/getAdminClinics")
	public List<AdminClinic> getAdminClinics(){
		return acs.getAdminClinics();
	}
	
	@PostMapping("/addAdminClinic")
	public int addDoctor(@RequestBody AdminClinic ac) {
		acs.addAdminClinic(ac);
		return 200;
	}
	
	
}
