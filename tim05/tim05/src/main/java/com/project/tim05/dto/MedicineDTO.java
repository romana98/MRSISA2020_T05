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
	
	private int id;
	
	public MedicineDTO(int id,String name, String description) {
		super();
		this.name = name;
		this.id = id;
		this.description = description;

	}

	

   public MedicineDTO(String name, String description) {
	super();
	this.name = name;
	this.description = description;

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



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}
	
	



}