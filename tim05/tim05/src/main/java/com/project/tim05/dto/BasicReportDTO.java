package com.project.tim05.dto;

import java.util.ArrayList;
import java.util.Date;

public class BasicReportDTO {
	
	private ClinicDTO clinic;
	private ArrayList<DoctorDTO> doctors;
	private double clinicAverageRate;
	private Date from;
	private Date to;
	private double income;
	
	public BasicReportDTO() {
		super();
		this.doctors = new ArrayList<DoctorDTO>();
	}

	public BasicReportDTO(ClinicDTO clinic, ArrayList<DoctorDTO> doctors, double clinicAverageRate, Date from, Date to,
			double income) {
		super();
		this.clinic = clinic;
		this.doctors = doctors;
		this.clinicAverageRate = clinicAverageRate;
		this.from = from;
		this.to = to;
		this.income = income;
	}

	public ClinicDTO getClinic() {
		return clinic;
	}

	public void setClinic(ClinicDTO clinic) {
		this.clinic = clinic;
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

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getTo() {
		return to;
	}

	public void setTo(Date to) {
		this.to = to;
	}

	public double getIncome() {
		return income;
	}

	public void setIncome(double income) {
		this.income = income;
	}
	
}
