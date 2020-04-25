package com.project.tim05.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="patients")
public class Patient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "patient_id", unique=true, nullable = false)
	private Integer id;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "surname", nullable = false)
	private String surname;
	
	@Column(name = "address", nullable = false)
	private String address;
	
	@Column(name = "city", nullable = false)
	private String city;
	
	@Column(name = "country", nullable = false)
	private String country;
	
	@Column(name = "phone_number", nullable = false)
	private String phone_number;
	
	@Column(name = "insurance_number", nullable = false)
	private String insurance_number;
	
	@ManyToOne(cascade = {CascadeType.ALL}, fetch=FetchType.LAZY)
	@JoinColumn(name="doctor", referencedColumnName="staff_id", nullable=true)
	private Doctor doctor;
	
	@ManyToOne(cascade = {CascadeType.ALL}, fetch=FetchType.LAZY)
	@JoinColumn(name="nurse", referencedColumnName="staff_id", nullable=true)
	private Nurse nurse;
		
	
	@OneToOne(cascade = {CascadeType.ALL}, fetch=FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name="medical_record", referencedColumnName="medical_record_id", nullable=false)
	private MedicalRecord medicalRecord;
	
	
	@OneToMany(cascade = {CascadeType.ALL}, fetch=FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "appointment_id")
	private Set<Appointment> appointments = new HashSet<Appointment>();
	
	@OneToMany(cascade = {CascadeType.ALL}, fetch=FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "clinic_id")
	private Set<Clinic> clinics = new HashSet<Clinic>();
	
	public Patient(){
		super();
		this.medicalRecord = new MedicalRecord();
	}
	
	public Patient(String email, String password, String name, String surname, String address, String city,
			String country, String phone_number, String insurance_number, MedicalRecord medicalRecord,
			Set<Appointment> appointments, Set<Clinic> clinics) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.city = city;
		this.country = country;
		this.phone_number = phone_number;
		this.insurance_number = insurance_number;
		this.medicalRecord = medicalRecord;
		this.appointments = appointments;
		this.clinics = clinics;
	}



	public Patient(String email,
			  String password,
			  String name,
			  String surname,
			  String address,
			  String city,
			  String country,
			  String phone_number,
			  String insurance_number) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.city = city;
		this.country = country;
		this.phone_number = phone_number;
		this.insurance_number = insurance_number;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
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
	
	

}
