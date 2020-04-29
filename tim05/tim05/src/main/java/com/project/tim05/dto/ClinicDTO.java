package com.project.tim05.dto;

import javax.validation.constraints.*;

public class ClinicDTO {
	
	@NotBlank 
	@NotNull
	@Pattern(regexp="[A-Za-z0-9 ]*")
	private String name;
	
	@NotBlank 
	@NotNull
	@Pattern(regexp="[A-Za-z0-9 ]*")
	private String address;
	
	@NotBlank 
	@NotNull
	private String description;
 
	public ClinicDTO() {
		super();
		
	}

	public ClinicDTO(String name, String address, String description) {
		super();
		this.name = name;
		this.address = address;
		this.description = description;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	

}