package com.project.tim05.dto;

import java.util.ArrayList;
import java.util.Date;

public class BasicReportDTO {
	
	private String clinic_name;
	private ArrayList<DoctorDTO> doctors;
	private double clinicAverageRate;
	private String from;
	private String to;
	private double income;
	
	public BasicReportDTO() {
		super();
		this.doctors = new ArrayList<DoctorDTO>();
	}

	

	

	public ArrayList<DoctorDTO> getDoctors() {
		return doctors;
	}

	public void setDoctors(ArrayList<DoctorDTO> doctors) {
		this.doctors = doctors;
	}

	public double getClinicAverageRate() {
		return clinicAverageRate;
	}

	public void setClinicAverageRate(double clinicAverageRate) {
		this.clinicAverageRate = clinicAverageRate;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public double getIncome() {
		return income;
	}

	public void setIncome(double income) {
		this.income = income;
	}

	public String getClinic_name() {
		return clinic_name;
	}

	public void setClinic_name(String clinic_name) {
		this.clinic_name = clinic_name;
	}
	
}
