package com.project.tim05.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class AppointmentTypeDTO {
	
	@NotBlank 
	@NotNull
	@Pattern(regexp="[A-Z][a-z]*")
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