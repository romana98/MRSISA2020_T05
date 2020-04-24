package com.project.tim05.model;


import javax.persistence.*;

@Entity
@Table(name="doctors")
public class Doctor extends MedicalStaff{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "doctor_id", unique=true, nullable = false)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="appointment_type", referencedColumnName="appointment_type_id", nullable=false)
	private AppointmentType appointmentType;
	
	
	public Doctor() {
		super();	
	}


	public Doctor(Integer id, AppointmentType appointmentType) {
		super();
		this.id = id;
		this.appointmentType = appointmentType;
	}


	public AppointmentType getAppointmentType() {
		return appointmentType;
	}

	public void setAppointmentType(AppointmentType appointmentType) {
		this.appointmentType = appointmentType;
	}
}
