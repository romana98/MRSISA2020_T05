package com.project.tim05.dto;

public class RegistrationRequestDTO {
   
   private String email;
   private String password;
   private String name;
   private String surname;
   private String address;
   private String city;
   private String town;
   private int number;
   private int insuranceId;

   
   public RegistrationRequestDTO() {
	super();
}

   public RegistrationRequestDTO(String email, String password, String name, String surname, String address, String city,
		String town, int number, int insuranceId) {
	super();
	this.email = email;
	this.password = password;
	this.name = name;
	this.surname = surname;
	this.address = address;
	this.city = city;
	this.town = town;
	this.number = number;
	this.insuranceId = insuranceId;
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
	
	public String getTown() {
		return town;
	}
	
	public void setTown(String town) {
		this.town = town;
	}
	
	public int getNumber() {
		return number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public int getInsuranceId() {
		return insuranceId;
	}
	
	public void setInsuranceId(int insuranceId) {
		this.insuranceId = insuranceId;
	}
}