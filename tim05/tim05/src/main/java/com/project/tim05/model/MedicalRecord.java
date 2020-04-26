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
	
	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="medicalRecord")
	private Set<Disease> diseases = new HashSet<Disease>();
	
	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="medicalRecord")
	private Set<Appointment> appointments = new HashSet<Appointment>();
	
	@OneToOne(cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
	private Patient patient;
	
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	
}