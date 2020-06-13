package com.project.tim05.dto;


import com.project.tim05.model.Appointment;



public class AppointmentMedicineDTO {
	
	private Integer id;
	

	private boolean authenticated;
	
	
	private Appointment appointment;
	
	private String description;
	

	private MedicineDTO medicine;

	private NurseDTO nurse;
	
	

	public AppointmentMedicineDTO() {
		super();
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	public MedicineDTO getMedicine() {
		return medicine;
	}

	public void setMedicine(MedicineDTO medicine) {
		this.medicine = medicine;
	}

	public NurseDTO getNurse() {
		return nurse;
	}

	public void setNurse(NurseDTO nurse) {
		this.nurse = nurse;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	
	


}
