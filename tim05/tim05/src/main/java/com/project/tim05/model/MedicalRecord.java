package com.project.tim05.model;

import java.util.*;

import javax.persistence.*;

@Entity
@Table(name="medical_records")
public class MedicalRecord {
   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "medical_record_id", unique=true, nullable = false)
	private Integer id; 
	
	@OneToMany(cascade = {CascadeType.ALL}, fetch=FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "disease_id")
	private Set<Disease> diseases = new HashSet<Disease>();
	
	@OneToMany(cascade = {CascadeType.ALL}, fetch=FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "appointment_id")
	private Set<Appointment> appointments = new HashSet<Appointment>();
	
	public MedicalRecord(Set<Disease> diseases, Set<Appointment> appointments) {
		super();
		this.diseases = diseases;
		this.appointments = appointments;
	}
	
	public MedicalRecord() {
		super();

	}

	public Set<Disease> getDiseases() {
		return diseases;
	}

	public void setDiseases(Set<Disease> diseases) {
		this.diseases = diseases;
	}

	public Set<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(Set<Appointment> appointments) {
		this.appointments = appointments;
	}
	
}