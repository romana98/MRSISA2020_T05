package com.project.tim05.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Table(name="clinic_center_admins")
@OnDelete(action = OnDeleteAction.CASCADE)
public class ClinicCenterAdministrator extends User {
  
	private static final long serialVersionUID = 1L;

	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="clinicCenterAdministrator")
	private Set<Clinic> clinics = new HashSet<Clinic>();
	
	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="clinicCenterAdministrator")
	private Set<RegistrationRequest> registrationRequests = new HashSet<RegistrationRequest>();
  
	
	public ClinicCenterAdministrator() {
			super();
		}

	
	public ClinicCenterAdministrator(String name, String surname, String email, String password) {
		super(email, password, name, surname);
	}


	public Set<Clinic> getClinics() {
		return clinics;
	}
	public void setClinics(Set<Clinic> clinics) {
		this.clinics = clinics;
	}
	public Set<RegistrationRequest> getRegistrationRequests() {
		return registrationRequests;
	}
	public void setRegistrationRequests(Set<RegistrationRequest> registrationRequests) {
		this.registrationRequests = registrationRequests;
	}
}