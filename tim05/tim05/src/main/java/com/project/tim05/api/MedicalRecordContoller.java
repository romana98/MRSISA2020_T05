package com.project.tim05.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.tim05.service.MedicalRecordService;
import com.project.tim05.service.PatientService;


@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/medicalRecord")
@RestController
public class MedicalRecordContoller {

	private final PatientService ps;
	private final MedicalRecordService mrs;
	
	@Autowired
	public MedicalRecordContoller(PatientService ps, MedicalRecordService mrs) {
		this.ps = ps;
		this.mrs = mrs;
	}
	
	
	
}
