package com.project.tim05.dto;

import javax.validation.constraints.*;

public class DiagnosisDTO {

	@NotBlank 
	@NotNull
	@Pattern(regexp="[A-Za-z0-9 ]*")
	private String name;
	
	@NotNull
	@NotBlank
	private String description;
	
   
	public DiagnosisDTO() {
		super();
		
	}
	public DiagnosisDTO(String name, String description) {
			super();
			this.name = name;
			this.description = description;
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
   
	
}