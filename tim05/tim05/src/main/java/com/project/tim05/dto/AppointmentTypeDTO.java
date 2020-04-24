package com.project.tim05.dto;

public class AppointmentTypeDTO {
	
	private String name;
	
	
	public AppointmentTypeDTO() {
		super();
		
	}

	public AppointmentTypeDTO(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}