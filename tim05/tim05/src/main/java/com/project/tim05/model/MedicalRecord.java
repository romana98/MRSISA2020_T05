package com.project.tim05.model;

import java.util.*;

import com.project.tim05.dto.AppointmentDTO;
import com.project.tim05.dto.DiseaseDTO;

public class MedicalRecord {
   
	private Set<Disease> diseases;
	private Set<Appointment> appointments;
	
	public MedicalRecord(Set<Disease> diseases, Set<Appointment> appointments) {
		super();
		this.diseases = diseases;
		this.appointments = appointments;
	}
	
	public MedicalRecord() {
		super();
		;
	}

	public Set<Disease> getDiseases() {
		return diseases;
	}

	public void setDiseases(Set<Disease> diseases) {
		this.diseases = diseases;
	}

	public Set<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(Set<Appointment> appointments) {
		this.appointments = appointments;
	}
	
	

}