package com.project.tim05.dao;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.project.tim05.model.AdminClinic;
import com.project.tim05.model.Doctor;

@Repository("DB")
public class DB implements DoctorAccess, AdminClinicAccess {

	ArrayList<Doctor> doctors = new ArrayList<Doctor>();
	ArrayList<AdminClinic> adminclinics = new ArrayList<AdminClinic>();
	
	@Override
	public void addDoctor(Doctor doctor) {
		this.doctors.add(doctor);
		
	}

	@Override
	public List<Doctor> getDoctors() {
		return this.doctors;
		
	}

	@Override
	public void addAdminClinic(AdminClinic ac) {
		this.adminclinics.add(ac);
		
	}

	@Override
	public List<AdminClinic> getAdminClinics() {
		return this.adminclinics;
	}

}
