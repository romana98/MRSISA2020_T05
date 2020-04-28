package com.project.tim05.model;

import javax.persistence.*;

@Entity
@Table(name="medicines", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "description"})})
public class Medicine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "medicine_id", unique=true, nullable = false)
	private Integer id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "authenticated", nullable = false)
	private boolean authenticated;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="appointment", referencedColumnName="appointment_id", nullable=true)
	private Appointment appointment;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="clinic_center_admin", referencedColumnName="clinic_center_admin_id", nullable=true)
	private ClinicCenterAdministrator clinicCenterAdministrator;
   
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="nurse", referencedColumnName="staff_id", nullable=true)
	private Nurse nurse;
   
	
		
	public Medicine() {
		super();
	}

	public Medicine(String name, String description, boolean authenticated) {
		super();
		this.name = name;
		this.description = description;
		this.authenticated = authenticated;
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

	public ClinicCenterAdministrator getClinicCenterAdministrator() {
		return clinicCenterAdministrator;
	}

	public void setClinicCenterAdministrator(ClinicCenterAdministrator clinicCenterAdministrator) {
		this.clinicCenterAdministrator = clinicCenterAdministrator;
	}
	
	
	
	
}