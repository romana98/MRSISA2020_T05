package com.project.tim05.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class MedicineDTO {
	
	@NotBlank 
	@NotNull
	@Pattern(regexp="([A-Z][a-z]+[ ]*){1,}")
    private String name;
	
	@NotBlank 
	@NotNull
	@Pattern(regexp="([A-Z][a-z]+[ a-z.,0-9]*){1,}")
	private String description;
	
	@NotNull
	private boolean authenticated;

   public MedicineDTO(String name, String description, boolean authenticated) {
	super();
	this.name = name;
	this.description = description;
	this.authenticated = authenticated;
}

	public MedicineDTO() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}


}