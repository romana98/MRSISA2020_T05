package com.project.tim05.dto;

import java.util.*;

public class AppointmentDTO {

	private Date dateTime;
	private int duration;
	private double price;
	private boolean request;
	private boolean finished;
	private boolean predefined;

	private int doctor_id;
	private int hall_id;
	private int appointmentType_id;
	
	private ClinicDTO clinic;
	private HallDTO hall;
	private DoctorDTO doctor;
	private AppointmentTypeDTO appointmentType;
	
	

	public AppointmentDTO() {
		super();
	}




	public AppointmentDTO(Date dateTime, int duration, double price, boolean request, boolean finished,
			ClinicDTO clinic, boolean predefined, HallDTO hall, DoctorDTO doctor, AppointmentTypeDTO appointmentType) {
		super();
		this.dateTime = dateTime;
		this.duration = duration;
		this.price = price;
		this.request = request;
		this.finished = finished;
		this.predefined = predefined;
		this.clinic = clinic;
		this.hall = hall;
		this.doctor = doctor;
		this.appointmentType = appointmentType;
	}
	
	
	

	public AppointmentDTO(Date dateTime, int duration, double price, boolean request,
			boolean predefined, int doctor_id, int hall_id, int appointmentType_id) {
		super();
		this.dateTime = dateTime;
		this.duration = duration;
		this.price = price;
		this.request = request;
		this.finished = false;
		this.predefined = predefined;
		this.doctor_id = doctor_id;
		this.hall_id = hall_id;
		this.appointmentType_id = appointmentType_id;
	}




	public int getDoctor_id() {
		return doctor_id;
	}



	public void setDoctor_id(int doctor_id) {
		this.doctor_id = doctor_id;
	}



	public int getHall_id() {
		return hall_id;
	}



	public void setHall_id(int hall_id) {
		this.hall_id = hall_id;
	}



	public int getAppointmentType_id() {
		return appointmentType_id;
	}



	public void setAppointmentType_id(int appointmentType_id) {
		this.appointmentType_id = appointmentType_id;
	}



	public boolean isPredefined() {
		return predefined;
	}

	public void setPredefined(boolean predefined) {
		this.predefined = predefined;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isRequest() {
		return request;
	}

	public void setRequest(boolean request) {
		this.request = request;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public ClinicDTO getClinic() {
		return clinic;
	}

	public void setClinic(ClinicDTO clinic) {
		this.clinic = clinic;
	}

	public HallDTO getHall() {
		return hall;
	}

	public void setHall(HallDTO hall) {
		this.hall = hall;
	}

	public DoctorDTO getDoctor() {
		return doctor;
	}

	public void setDoctor(DoctorDTO doctor) {
		this.doctor = doctor;
	}

	public AppointmentTypeDTO getAppointmentType() {
		return appointmentType;
	}

	public void setAppointmentType(AppointmentTypeDTO appointmentType) {
		this.appointmentType = appointmentType;
	}


}