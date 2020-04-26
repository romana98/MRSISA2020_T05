package com.project.tim05.dto;


public class ClinicAdministratorDTO {
   private String name;
   private String surname;
   private String password;
   private String email;
   
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