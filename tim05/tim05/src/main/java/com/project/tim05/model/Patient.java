package com.project.tim05.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="patients")
@OnDelete(action = OnDeleteAction.CASCADE)
public class Patient extends User {
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "address", nullable = false)
	private String address;
	
	@Column(name = "city", nullable = false)
	private String city;
	
	@Column(name = "country", nullable = false)
	private String country;
	
	@Column(name = "phone_number", nullable = false)
	private String phone_number;
	
	@Column(name = "insurance_number", unique = true, nullable = false)
	private String insurance_number;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="doctor", referencedColumnName="user_id", nullable=true)
	private Doctor doctor;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="nurse", referencedColumnName="user_id", nullable=true)
	private Nurse nurse;		
	
	@OneToOne(cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
	@JoinColumn(name="medical_record", nullable=false)
	private MedicalRecord medicalRecord;
		
	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="patient")
	private Set<Appointment> appointments = new HashSet<Appointment>();
	

	@ManyToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="clinic", referencedColumnName="clinic_id", nullable=true)
	private Set<Clinic> clinics = new HashSet<Clinic>();


	public Patient(){
		super();
		this.medicalRecord = new MedicalRecord();
		this.setEnabled(true);
	}
	
	
	public Patient(String email, String password, String name, String surname) {
		super(email, password, name, surname);
	}
	
	public Patient(String email, String name, String surname, String address2, String city2,
			String country2, String phone_number2, String insurance_number2) {
		super(email, name, surname);
		this.address = address2;
		this.city = city2;
		this.country = country2;
		this.phone_number = phone_number2;
		this.insurance_number = insurance_number2;
	}


	public Patient(String email, String password, String name, String surname, String address2, String city2,
			String country2, String phone_number2, String insurance_number2) {
		super(email, password, name, surname);
		this.address = address2;
		this.city = city2;
		this.country = country2;
		this.phone_number = phone_number2;
		this.insurance_number = insurance_number2;
	}


	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getInsurance_number() {
		return insurance_number;
	}

	public void setInsurance_number(String insurance_number) {
		this.insurance_number = insurance_number;
	}

	public MedicalRecord getMedicalRecord() {
		return medicalRecord;
	}

	public void setMedicalRecord(MedicalRecord medicalRecord) {
		this.medicalRecord = medicalRecord;
	}

	public Set<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(Set<Appointment> appointments) {
		this.appointments = appointments;
	}

	public Set<Clinic> getClinics() {
		return clinics;
	}

	public void setClinics(Set<Clinic> clinics) {
		this.clinics = clinics;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Nurse getNurse() {
		return nurse;
	}

	public void setNurse(Nurse nurse) {
		this.nurse = nurse;
	}


}
