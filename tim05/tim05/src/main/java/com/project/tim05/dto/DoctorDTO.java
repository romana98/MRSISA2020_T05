package com.project.tim05.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class DoctorDTO extends MedicalStaffDTO {
	
	//private AppointmentTypeDTO appointmentType;
	@NotBlank 
	@NotNull
	private int appointment_type_id;
	
	@NotBlank 
	@NotNull
	private int clinic_id;

	public DoctorDTO() {
		super();
		
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


	/*public DoctorDTO(AppointmentTypeDTO appointmentType) {

		this.appointmentType = appointmentType;
	}

	public AppointmentTypeDTO getAppointmentType() {
		return appointmentType;
	}

	public void setAppointmentType(AppointmentTypeDTO appointmentType) {
		this.appointmentType = appointmentType;
	}*/
	
	
}