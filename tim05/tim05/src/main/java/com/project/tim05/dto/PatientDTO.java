package com.project.tim05.dto;


public class PatientDTO {
   
   private String email;
   private String password;
   private String name;
   private String surname;
   private String address;
   private String city;
   private String country;
   private String phone_number;
   private String insurance_number;
   
   private MedicalRecordDTO medicalRecord;
   
 
   public PatientDTO() {
		super();
	}

	public PatientDTO(String email, String password, String name, String surname, String address, String city, String town,
			String phone_number, String insurance_number, MedicalRecordDTO medicalRecord) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.city = city;
		this.country = town;
		this.phone_number = phone_number;
		this.insurance_number = insurance_number;
		this.medicalRecord = medicalRecord;
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

	public MedicalRecordDTO getMedicalRecord() {
		return medicalRecord;
	}
	
	public void setMedicalRecord(MedicalRecordDTO medicalRecord) {
		this.medicalRecord = medicalRecord;
	}

}