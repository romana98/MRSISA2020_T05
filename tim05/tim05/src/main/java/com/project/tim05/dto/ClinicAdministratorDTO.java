package com.project.tim05.dto;

import javax.validation.constraints.*;

import org.hibernate.validator.constraints.Length;


public class ClinicAdministratorDTO {
	
	@NotBlank 
	@NotNull
	@Pattern(regexp="[A-Z][a-z]*")
    private String name;
	
	@NotBlank 
	@NotNull
	@Pattern(regexp="[A-Z][a-z]*")
    private String surname;
	
	@NotBlank 
	@NotNull
	@Length(min=8)
    private String password;
	
	@NotBlank 
	@NotNull
	@Pattern(regexp="[a-z]+[a-z0-9._]*[a-z0-9]+@[a-z]*.com")
    private String email;
   
	@NotNull
    private ClinicDTO clinic;
   
 
    public ClinicAdministratorDTO() {
    	super();
	
   }

	public ClinicAdministratorDTO(String name, String surname, String password, String email, ClinicDTO clinic) {
		super();
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.email = email;
		this.clinic = clinic;
		
	}
	 
	public ClinicAdministratorDTO(String name2, String surname2, String email2, @NotNull ClinicDTO clinic2) {
		this.name = name2;
		this.surname = surname2;
		this.email = email2;
		this.clinic = clinic2;
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
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public ClinicDTO getClinic() {
		return clinic;
	}
	
	public void setClinic(ClinicDTO clinic) {
		this.clinic = clinic;
	}
	   
	   

}