package com.project.tim05.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="doctors")
@OnDelete(action = OnDeleteAction.CASCADE)
public class Doctor extends MedicalStaff {

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "clinic_admin", referencedColumnName = "user_id", nullable = true)
	private ClinicAdministrator clinicAdministrator;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "clinic", referencedColumnName = "clinic_id", nullable = true)
	private Clinic clinic;

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "doctor")
	private Set<Appointment> appointments = new HashSet<Appointment>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "appointment_type", referencedColumnName = "appointment_type_id", nullable = true)
	private AppointmentType appointmentType;

	public Doctor() {
		super();
	}

	public Doctor(Integer id, AppointmentType appointmentType) {
		super();
		this.appointmentType = appointmentType;
	}

	public AppointmentType getAppointmentType() {
		return appointmentType;
	}

	public void setAppointmentType(AppointmentType appointmentType) {
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
