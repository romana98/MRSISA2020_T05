package com.project.tim05.model;

import java.util.*;

import javax.persistence.*;


@Entity
@Table(name="clinic_center_admins")
public class ClinicCenterAdministrator extends User {
  
	private static final long serialVersionUID = 1L;

	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="clinicCenterAdministrator")
	private Set<Clinic> clinics = new HashSet<Clinic>();
	
	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="clinicCenterAdministrator")
	private Set<Diagnosis> diagnosises = new HashSet<Diagnosis>();
	
	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="clinicCenterAdministrator")
	private Set<Medicine> medicines = new HashSet<Medicine>();
	
	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="clinicCenterAdministrator")
	private Set<RegistrationRequest> registrationRequests = new HashSet<RegistrationRequest>();
  
	
	public ClinicCenterAdministrator() {
			super();
		}

	
	public ClinicCenterAdministrator(String email, String password, String name, String surname) {
		super(email, password, name, surname);
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


	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}
	   
   
}