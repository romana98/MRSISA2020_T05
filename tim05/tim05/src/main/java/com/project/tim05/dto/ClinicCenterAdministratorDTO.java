package com.project.tim05.dto;

import javax.validation.constraints.*;

public class ClinicCenterAdministratorDTO {
  
	@NotBlank 
	@NotNull
	@Pattern(regexp="[A-Za-z ]*")
	private String name;
   
	@NotBlank 
	@NotNull
	@Pattern(regexp="[A-Za-z ]*")
	private String email;
  
	@NotBlank 
	@NotNull
	@Min(value=8)
	private String password;
   
	@NotBlank 
	@NotNull
	@Pattern(regexp="^(.+)@(.+)$")
	private String surname;
   
	public ClinicCenterAdministratorDTO() {
		super();
	}

	public ClinicCenterAdministratorDTO(String name, String email, String password, String surname) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.surname = surname;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
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
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	   
   

}