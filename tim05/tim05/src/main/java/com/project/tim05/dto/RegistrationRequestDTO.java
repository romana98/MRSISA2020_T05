package com.project.tim05.dto;

import javax.validation.constraints.*;


public class RegistrationRequestDTO {
   
	@NotBlank 
	@NotNull
	@Pattern(regexp="^(.+)@(.+)$")
	private String email;
	
	@NotBlank 
	@NotNull
	@Min(value=8)
	private String password;
    @NotBlank 
	@NotNull
	@Pattern(regexp="[A-Za-z ]*")
    private String name;
    
    @NotBlank 
	@NotNull
	@Pattern(regexp="[A-Za-z ]*")
    private String surname;
  
    @NotBlank 
	@NotNull
    private String address;
   
    @NotBlank 
	@NotNull
    private String city;
   
    @NotBlank 
	@NotNull
    private String country;
   
    @NotBlank 
	@NotNull
    private String phone_number;
   
    @NotBlank 
	@NotNull
    private String insurance_number;

   
   public RegistrationRequestDTO() {
	super();
   }
   
 

   public RegistrationRequestDTO(String email, String password, String name, String surname, String address, String city,
		String country, String number, String insuranceId) {
	super();
	this.email = email;
	this.password = password;
	this.name = name;
	this.surname = surname;
	this.address = address;
	this.city = city;
	this.country = country;
	this.phone_number = number;
	this.insurance_number = insuranceId;
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
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getInsurance_number() {
		return insurance_number;
	}

	public void setInsurance_number(String insurance_number) {
		this.insurance_number = insurance_number;
	}
	
	
}