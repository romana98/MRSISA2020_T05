package com.project.tim05.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="appointment_medicines")
public class AppointmentMedicine {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "medicine_id", unique=true, nullable = false)
	private Integer id;
	
	@Column(name = "authenticated", nullable = true)
	private boolean authenticated;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="appointment", referencedColumnName="appointment_id", nullable=true)
	private Appointment appointment;
	
	@OneToOne( fetch=FetchType.LAZY)
	private Medicine medicine;
	
   
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="nurse", referencedColumnName="user_id", nullable=true)
	private Nurse nurse;
	
	
	

	public AppointmentMedicine() {
		super();
	}

	public boolean isAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	public Nurse getNurse() {
		return nurse;
	}

	public void setNurse(Nurse nurse) {
		this.nurse = nurse;
	}

	public Medicine getMedicine() {
		return medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}
	
	

}
