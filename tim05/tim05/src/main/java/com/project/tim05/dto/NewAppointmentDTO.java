package com.project.tim05.dto;

import java.util.ArrayList;
import java.util.List;

public class NewAppointmentDTO {

	String date;

	ArrayList<DoctorDTO> doctors;
	
	boolean busy;
	
	String appointment_id;
	
	String hall_id;
	
	String doctor_id;
	
	List<String> ids;
	
	
	
	public String getAppointment_id() {
		return appointment_id;
	}

	public void setAppointment_id(String appointment_id) {
		this.appointment_id = appointment_id;
	}

	public String getHall_id() {
		return hall_id;
	}

	public void setHall_id(String hall_id) {
		this.hall_id = hall_id;
	}

	public String getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(String doctor_id) {
		this.doctor_id = doctor_id;
	}

	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	public boolean isBusy() {
		return busy;
	}

	public void setBusy(boolean busy) {
		this.busy = busy;
	}

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
