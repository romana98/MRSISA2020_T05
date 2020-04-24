package com.project.tim05.dto;

public class DoctorDTO extends MedicalStaffDTO {
	
	private AppointmentTypeDTO appointmentType;
	

	public DoctorDTO() {
		super();
		
	}

	public DoctorDTO(AppointmentTypeDTO appointmentType) {

		this.appointmentType = appointmentType;
	}

	public AppointmentTypeDTO getAppointmentType() {
		return appointmentType;
	}

	public void setAppointmentType(AppointmentTypeDTO appointmentType) {
		this.appointmentType = appointmentType;
	}
	
	
}