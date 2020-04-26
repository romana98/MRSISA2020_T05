package com.project.tim05.dto;

public class RegistrationRequestDTO {
   
	
   private String email;
   private String password;
   private String name;
   private String surname;
   private String address;
   private String city;
   private String country;
   private String phone_number;
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
	
	public String getNumber() {
		return phone_number;
	}
	
	public void setNumber(String number) {
		this.phone_number = number;
	}
	
	public String getInsuranceId() {
		return insurance_number;
	}
	
	public void setInsuranceId(String insuranceId) {
		this.insurance_number = insuranceId;
	}
}