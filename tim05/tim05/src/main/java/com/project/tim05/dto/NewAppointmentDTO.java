package com.project.tim05.dto;

import java.util.ArrayList;

import com.project.tim05.model.Doctor;

public class NewAppointmentDTO {

	String date;

	ArrayList<DoctorDTO> doctors;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public ArrayList<DoctorDTO> getDoctors() {
		return doctors;
	}

	public void setDoctors(ArrayList<DoctorDTO> doctors) {
		this.doctors = doctors;
	}
	
	

}
