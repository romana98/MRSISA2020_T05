package com.project.tim05.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class AppointmentTypeDTO {
	
	@NotBlank 
	@NotNull
	@Pattern(regexp="[A-Z][a-z]*")
	private String name;
	
	@NotNull
	private int admin_id;
	
	public AppointmentTypeDTO() {
		super();
		
	}

	public AppointmentTypeDTO(String name) {
		super();
		this.name = name;
	}
	
	

	public int getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}