package com.project.tim05.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.project.tim05.dao.DoctorAccess;
import com.project.tim05.model.Doctor;

@Service
public class DoctorService {

	private final DoctorAccess da;

	@Autowired
	public DoctorService(@Qualifier("DB") DoctorAccess da) {
		this.da = da;
	}
	
	public void addDoctor(Doctor doctor) {
		da.addDoctor(doctor);
	}
	
	public List<Doctor> getDoctors(){
		return da.getDoctors();
	}
}
