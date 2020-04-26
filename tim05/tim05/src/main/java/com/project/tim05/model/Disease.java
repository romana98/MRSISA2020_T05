package com.project.tim05.model;

import javax.persistence.*;

@Entity
@Table(name="diseases")
public class Disease {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "disease_id", unique=true, nullable = false)
	private Integer id;
	
	@Column(name = "name", nullable = false)
	private String name;
  
	@Column(name = "value", nullable = false)
	private String value;
   
	@Column(name = "description", nullable = false)
	private String description;
	
	@ManyToOne(cascade = {CascadeType.MERGE}, fetch=FetchType.LAZY)
	@JoinColumn(name="appointment", referencedColumnName="appointment_id", nullable=true)
	private Appointment appointment;

	@ManyToOne(cascade = {CascadeType.MERGE}, fetch=FetchType.LAZY)
	@JoinColumn(name="medical_record", referencedColumnName="medical_record_id", nullable=true)
	private MedicalRecord medicalRecord;
   
	

	public Disease() {
		super();
		
	}

	public Disease(Integer id, String name, String value, String description) {
		super();
		this.id = id;
		this.name = name;
		this.value = value;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public MedicalRecord getMedicalRecord() {
		return medicalRecord;
	}

	public void setMedicalRecord(MedicalRecord medicalRecord) {
		this.medicalRecord = medicalRecord;
	}
	
	

}