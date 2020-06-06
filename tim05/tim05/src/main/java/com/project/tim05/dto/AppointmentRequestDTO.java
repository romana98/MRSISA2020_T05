package com.project.tim05.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AppointmentRequestDTO {

	@NotBlank
	@NotNull
	private String date;
	@NotBlank
	@NotNull
	private String time;

	@NotBlank
	@NotNull
	private String app_type;
	@NotBlank
	@NotNull
	private String doctor;
	@NotBlank
	@NotNull
	private String clinic;
	@NotBlank
	@NotNull
	private String patient;

	public AppointmentRequestDTO() {
		super();
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getApp_type() {
		return app_type;
	}

	public void setApp_type(String app_type) {
		this.app_type = app_type;
	}

	public String getDoctor() {
		return doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}

	public String getClinic() {
		return clinic;
	}

	public void setClinic(String clinic) {
		this.clinic = clinic;
	}

	public String getPatient() {
		return patient;
	}

	public void setPatient(String patient) {
		this.patient = patient;
	}

}
