package com.project.tim05.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="clinic_admins")
public class ClinicAdministrator {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "clinic_admin_id", unique=true, nullable = false)
	private Integer id;
	
	@Column(name = "name", nullable = false)
	private String name;
   
	@Column(name = "surname", nullable = false)
	private String surname;
  
	@Column(name = "password", nullable = false)
	private String password;
   
	@Column(name = "email", nullable = false)
	private String email;
   
	@OneToOne( fetch=FetchType.LAZY)
	@JoinColumn(name="clinic", nullable=false)
	private Clinic clinic;
   
	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="clinicAdministrator")
	private Set<Appointment> appointments = new HashSet<Appointment>();
   
	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="clinicAdministrator")
	private Set<Hall> halls = new HashSet<Hall>();

	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="clinicAdministrator")
	private Set<Doctor> doctors = new HashSet<Doctor>();
   
	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="clinicAdministrator")
	private Set<AppointmentType> appointmentTypes = new HashSet<AppointmentType>();
   
	
	public ClinicAdministrator() {
			super();
		}
	
	public ClinicAdministrator(String name, String surname, String email, String password, Clinic clinic)
	{
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.email = email;
		this.clinic = clinic;
		
	}
	
	public ClinicAdministrator(Integer id, String name, String surname, String password, String email, Clinic clinic,
			Set<Appointment> appointments, Set<Hall> halls, Set<Doctor> doctors, Set<AppointmentType> appointmentTypes) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.email = email;
		this.clinic = clinic;
		this.appointments = appointments;
		this.halls = halls;
		this.doctors = doctors;
		this.appointmentTypes = appointmentTypes;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Set<AppointmentType> getAppointmentTypes() {
		return appointmentTypes;
	}

	public void setAppointmentTypes(Set<AppointmentType> appointmentTypes) {
		this.appointmentTypes = appointmentTypes;
	}
	
	
}