package com.project.tim05.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

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
	@JoinColumn(name="clinic", referencedColumnName="clinic_id", nullable=true)
	private Clinic clinic;
   
	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="appointmentType")
	private Set<Appointment> doctors = new HashSet<Appointment>();
	
	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="appointmentType")
	private Set<Appointment> appointments = new HashSet<Appointment>();

	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="appointmentType")
	private Set<Pricelist> priceList = new HashSet<Pricelist>();
	
	@Version
	private Long version;

	
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


	public Clinic getClinic() {
		return clinic;
	}


	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}


	public Set<Pricelist> getPriceList() {
		return priceList;
	}


	public void setPriceList(Set<Pricelist> priceList) {
		this.priceList = priceList;
	}
	
	
}
