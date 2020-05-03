package com.project.tim05.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="appointment_types")
public class AppointmentType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "appointment_type_id", unique=true, nullable = false)
	private Integer id;
	
	@Column(name = "name", nullable = false, unique = true)
	private String name;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="clinic_admin", referencedColumnName="user_id", nullable=true)
	private ClinicAdministrator clinicAdministrator;
   
	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="appointmentType")
	private Set<Appointment> doctors = new HashSet<Appointment>();
	
	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="appointmentType")
	private Set<Appointment> appointments = new HashSet<Appointment>();

	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="appointmentType")
	private Set<Pricelist> priceList = new HashSet<Pricelist>();

	
	public AppointmentType() {
		super();
	}


	public AppointmentType(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public AppointmentType(String name) {
		super();
		this.name = name;
	}

	public Set<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(Set<Appointment> appointments) {
		this.appointments = appointments;
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


	public ClinicAdministrator getClinicAdmin() {
		return clinicAdministrator;
	}


	public void setClinicAdmin(ClinicAdministrator clinicAdmin) {
		this.clinicAdministrator = clinicAdmin;
	}


	public Set<Pricelist> getPriceList() {
		return priceList;
	}


	public void setPriceList(Set<Pricelist> priceList) {
		this.priceList = priceList;
	}
	
	
}
