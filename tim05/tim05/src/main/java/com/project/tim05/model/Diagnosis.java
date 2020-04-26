package com.project.tim05.model;

import javax.persistence.*;


@Entity
@Table(name="diagnosises")
public class Diagnosis {
   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "diagnosis_id", unique=true, nullable = false)
	private Integer id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="appointment", referencedColumnName="appointment_id", nullable=true)
	private Appointment appointment;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="clinic_center_admin", referencedColumnName="clinic_center_admin_id", nullable=true)
	private ClinicCenterAdministrator clinicCenterAdministrator;
   	

	public Diagnosis() {
		super();
		
	}
	
	public Diagnosis(String name, String description) {
		this.name = name;
		this.description = description;
		
	}

	public Diagnosis(Integer id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public ClinicCenterAdministrator getClinicCenterAdministrator() {
		return clinicCenterAdministrator;
	}

	public void setClinicCenterAdministrator(ClinicCenterAdministrator clinicCenterAdministrator) {
		this.clinicCenterAdministrator = clinicCenterAdministrator;
	}
	
	
	

}