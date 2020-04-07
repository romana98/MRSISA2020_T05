package com.project.tim05.dao;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.project.tim05.model.Doctor;

@Repository("DB")
public class DB implements DoctorAccess {

	ArrayList<Doctor> doctors = new ArrayList<Doctor>();
	
	@Override
	public void addDoctor(Doctor doctor) {
		this.doctors.add(doctor);
		
	}

	@Override
	public List<Doctor> getDoctors() {
		return this.doctors;
		
	}

}
