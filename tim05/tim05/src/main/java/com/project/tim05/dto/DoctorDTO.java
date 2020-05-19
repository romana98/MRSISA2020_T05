package com.project.tim05.dto;

import javax.validation.constraints.NotNull;

public class DoctorDTO extends MedicalStaffDTO {

	// private AppointmentTypeDTO appointmentType;
	@NotNull
	private int appointment_type_id;

	@NotNull
	private int clinic_id;

	@NotNull
	private int id;
 
	private String AppointmentTypeName;

	public DoctorDTO() {
		super();

	}
	
	public String getAppointmentTypeName() {
		return AppointmentTypeName;
	}



	public void setAppointmentTypeName(String appointmentTypeName) {
		AppointmentTypeName = appointmentTypeName;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getClinic_id() {
		return clinic_id;
	}

	public void setClinic_id(int clinic_id) {
		this.clinic_id = clinic_id;
	}

	public int getAppointment_type_id() {
		return appointment_type_id;
	}

	public void setAppointment_type_id(int appointment_type_id) {
		this.appointment_type_id = appointment_type_id;
	}

	/*
	 * public DoctorDTO(AppointmentTypeDTO appointmentType) {
	 * 
	 * this.appointmentType = appointmentType; }
	 * 
	 * public AppointmentTypeDTO getAppointmentType() { return appointmentType; }
	 * 
	 * public void setAppointmentType(AppointmentTypeDTO appointmentType) {
	 * this.appointmentType = appointmentType; }
	 */

}