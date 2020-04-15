package com.project.tim05.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.project.tim05.dao.DoctorAccess;
import com.project.tim05.model.Doctor;
import com.project.tim05.repository.DoctorRepository;

@Service
public class DoctorService {

	@Autowired
	private DoctorRepository dr;
	
	public void addDoctor(Doctor doctor) {
		dr.save(doctor);
	}
	
	public List<Doctor> getDoctors(){
		return dr.findAll();
	}
}
