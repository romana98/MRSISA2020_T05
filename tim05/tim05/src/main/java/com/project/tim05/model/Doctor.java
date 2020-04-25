package com.project.tim05.model;


import javax.persistence.*;

@Entity
@Table(name="doctors")
public class Doctor extends MedicalStaff{
	
	
	//mockupujem ovde string dok ne budemo imali tipove jer ovako puca
	/*@ManyToOne
	@JoinColumn(name="appointment_type", referencedColumnName="appointment_type_id", nullable=false)
	private AppointmentType appointmentType;*/
	
	@ManyToOne(cascade = {CascadeType.ALL}, fetch=FetchType.LAZY)
	@JoinColumn(name="clinicAdminstrator", referencedColumnName="clinic_admin_id", nullable=true)
	private ClinicAdministrator clinicAdministrator;
	
	@ManyToOne(cascade = {CascadeType.ALL}, fetch=FetchType.LAZY)
	@JoinColumn(name="clinicForeign", referencedColumnName="clinic_id", nullable=true)
	private Clinic clinic;
	
	@Column(name = "appointmentType" , nullable = false)
	private String appointmentType;
	
	
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
}
