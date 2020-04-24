package com.project.tim05.model;

import java.util.*;

import javax.persistence.*;


@Entity
@Table(name="clinic_center_admins")
public class ClinicCenterAdministrator {
  
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "clinic_center_admin_id", unique=true, nullable = false)
	private Integer id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "surname", nullable = false)
	private String surname;
   
	@OneToMany(cascade = {CascadeType.ALL}, fetch=FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "clinic_id")
	private Set<Clinic> clinics = new HashSet<Clinic>();
	
	@OneToMany(cascade = {CascadeType.ALL}, fetch=FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "diagnosis_id")
	private Set<Diagnosis> diagnosises = new HashSet<Diagnosis>();
	
	@OneToMany(cascade = {CascadeType.ALL}, fetch=FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "medicine_id")
	private Set<Medicine> medicines = new HashSet<Medicine>();
	
	@OneToMany(cascade = {CascadeType.ALL}, fetch=FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "registration_request_id")
	private Set<RegistrationRequest> registrationRequests = new HashSet<RegistrationRequest>();
  
	
public ClinicCenterAdministrator() {
		super();
	}

public ClinicCenterAdministrator(Integer id, String name, String email, String password, String surname,
			Set<Clinic> clinics, Set<Diagnosis> diagnosises, Set<Medicine> medicines,
			Set<RegistrationRequest> registrationRequests) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.surname = surname;
		this.clinics = clinics;
		this.diagnosises = diagnosises;
		this.medicines = medicines;
		this.registrationRequests = registrationRequests;
	}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
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
public String getSurname() {
	return surname;
}
public void setSurname(String surname) {
	this.surname = surname;
}
public Set<Clinic> getClinics() {
	return clinics;
}
public void setClinics(Set<Clinic> clinics) {
	this.clinics = clinics;
}
public Set<Diagnosis> getDiagnosises() {
	return diagnosises;
}
public void setDiagnosises(Set<Diagnosis> diagnosises) {
	this.diagnosises = diagnosises;
}
public Set<Medicine> getMedicines() {
	return medicines;
}
public void setMedicines(Set<Medicine> medicines) {
	this.medicines = medicines;
}
public Set<RegistrationRequest> getRegistrationRequests() {
	return registrationRequests;
}
public void setRegistrationRequests(Set<RegistrationRequest> registrationRequests) {
	this.registrationRequests = registrationRequests;
}
   
   
   
   

}