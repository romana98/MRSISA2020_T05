package com.project.tim05.dto;

import javax.validation.constraints.*;

public class DiagnosisDTO {

	@NotBlank 
	@NotNull
	@Pattern(regexp="([A-Z][a-z]+[ ]*){1,}")
	private String name;
	
	@NotNull
	@NotBlank
	@Pattern(regexp="([A-Z][a-z]+[ a-z.,0-9]*){1,}")
	private String description;
	
	private String date;
	
	int id;
	
   
	public DiagnosisDTO() {
		super();
		
	}
	public DiagnosisDTO(String name, String description) {
			super();
			this.name = name;
			this.description = description;
		}
	
	
	
	public DiagnosisDTO(int id, String name, String description) {
		super();
		this.name = name;
		this.description = description;
		this.id = id;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
   
	
}