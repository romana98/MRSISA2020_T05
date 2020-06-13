package com.project.tim05.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

public class DoctorDTO extends MedicalStaffDTO {

	// private AppointmentTypeDTO appointmentType;
	@NotNull
	private int appointment_type_id;

	@NotNull
	private int clinic_id;

	private int id;

	// private String name;

	// private String surname;

	private String AppointmentTypeName;

	private double average_rate;

	private List<String> available_times;
	private String time;

	public DoctorDTO() {
		super();

	}

	public DoctorDTO(String name, String surname) {
		super(name, surname);
	}

	public DoctorDTO(Integer id2, String name, String surname) {
		super(name, surname);
		this.id = id2;
	}

	public int getId() {
		return id;
	}

	public List<String> getAvailable_times() {
		return available_times;
	}

	public void setAvailable_times(List<String> available_times) {
		this.available_times = available_times;
	}

	public double getAverage_rate() {
		return average_rate;
	}

	public void setAverage_rate(double average_rate) {
		this.average_rate = average_rate;
	}

	public String getAppointmentTypeName() {
		return AppointmentTypeName;
	}

	public void setAppointmentTypeName(String appointmentTypeName) {
		AppointmentTypeName = appointmentTypeName;
	}

	/*
	 * public String getName() { return super.getName(); }
	 * 
	 * public void setName(String name) { super.setName(super.getName()); }
	 * 
	 * public String getSurname() { return super.getSurname(); }
	 * 
	 * public void setSurname(String surname) {
	 * super.setSurname(super.getSurname()); }
	 * 
	 * public int getId() { return id; }
	 */

	public void setId(int id) {
		this.id = id;
	}

	public int getClinic_id() {
		return clinic_id;
	}

	public void setClinic_id(int clinic_id) {
		this.clinic_id = clinic_id;
	}

	public int getAppointment_type_id() {
		return appointment_type_id;
	}

	public void setAppointment_type_id(int appointment_type_id) {
		this.appointment_type_id = appointment_type_id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	/*
	 * public DoctorDTO(AppointmentTypeDTO appointmentType) {
	 * 
	 * this.appointmentType = appointmentType; }
	 * 
	 * public AppointmentTypeDTO getAppointmentType() { return appointmentType; }
	 * 
	 * public void setAppointmentType(AppointmentTypeDTO appointmentType) {
	 * this.appointmentType = appointmentType; }
	 */

}