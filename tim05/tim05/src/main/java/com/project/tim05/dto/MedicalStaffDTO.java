package com.project.tim05.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

public class MedicalStaffDTO {
   
	@NotBlank 
	@NotNull
	@Pattern(regexp="[A-Z][a-z]*")
   private String name;
	
	@NotBlank 
	@NotNull
	@Pattern(regexp="[A-Z][a-z]*")
   private String surname;
	
   private String email;
	
	@NotBlank 
	@NotNull
	@Length(min=8)
   private String password;
	
	private String type;
   
   
   public MedicalStaffDTO() {
	super();
}

public MedicalStaffDTO(String name, String surname, String email, String password, String workStart, String workEnd,
		String type) {
	super();
	this.name = name;
	this.surname = surname;
	this.email = email;
	this.password = password;
	this.type = type;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getSurname() {
	return surname;
}

public void setSurname(String surname) {
	this.surname = surname;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}


public String getType() {
	return type;
}

public void setType(String type) {
	this.type = type;
}
   
   
}