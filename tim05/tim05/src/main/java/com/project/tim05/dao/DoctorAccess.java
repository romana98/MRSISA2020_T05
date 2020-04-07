package com.project.tim05.dao;
import java.util.List;

import com.project.tim05.model.Doctor;

public interface DoctorAccess {
	
	void addDoctor(Doctor doctor);
	
	List<Doctor> getDoctors();
	
}
