package com.project.tim05.model;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="doctors")
public class Doctor extends MedicalStaff{
	
	
	//mockupujem ovde string dok ne budemo imali tipove jer ovako puca
	/*@ManyToOne
	@JoinColumn(name="appointment_type", referencedColumnName="appointment_type_id", nullable=false)
	private AppointmentType appointmentType;*/
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="clinic_admin", referencedColumnName="clinic_admin_id", nullable=true)
	private ClinicAdministrator clinicAdministrator;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="clinic", referencedColumnName="clinic_id", nullable=true)

	private Clinic clinic;
	
	@Column(name = "appointmentType" , nullable = false)
	private String appointmentType;
	
	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="doctor")
	private Set<Appointment> appointments = new HashSet<Appointment>();

	
	public Doctor() {
		super();	
	}

	public Doctor(Integer id, String appointmentType) {
		super();
		this.appointmentType = appointmentType;
	}


	public String getAppointmentType() {
		return appointmentType;
	}

	public void setAppointmentType(String appointmentType) {
		this.appointmentType = appointmentType;
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

	public ClinicAdministrator getClinicAdmin() {
		return clinicAdministrator;
	}

	public void setClinicAdmin(ClinicAdministrator clinicAdmin) {
		this.clinicAdministrator = clinicAdmin;
	}
	
	
}
