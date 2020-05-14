package com.project.tim05.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="clinic_admins")
@OnDelete(action = OnDeleteAction.CASCADE)
public class ClinicAdministrator extends User {

	private static final long serialVersionUID = 1L;
	
	@OneToOne( fetch=FetchType.LAZY)
	@JoinColumn(name="clinic", nullable=false)
	private Clinic clinic;
   
	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="clinicAdministrator")
	private Set<Appointment> appointments = new HashSet<Appointment>();
   
	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="clinicAdministrator")
	private Set<Hall> halls = new HashSet<Hall>();

	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="clinicAdministrator")
	private Set<Doctor> doctors = new HashSet<Doctor>();
   
   
	
	public ClinicAdministrator() {
			super();
		}



	public ClinicAdministrator(String name, String surname, String email, String password, Clinic cl) {
		super(email, password, name, surname);
		this.clinic = cl;
	}

	public Clinic getClinic() {
		return clinic;
	}

	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}

	public Set<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(Set<Appointment> appointments) {
		this.appointments = appointments;
	}

	public Set<Hall> getHalls() {
		return halls;
	}

	public void setHalls(Set<Hall> halls) {
		this.halls = halls;
	}

	public Set<Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(Set<Doctor> doctors) {
		this.doctors = doctors;
	}


}