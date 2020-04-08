package com.project.tim05.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Patient {
	
	private String email;
	private String password;
	private String first_name;
	private String last_name;
	private String address;
	private String city;
	private String country;
	private String phone_number;
	private String unique_num;
	
	public Patient(@JsonProperty("email") String email,
			  @JsonProperty("password") String password,
			  @JsonProperty("first_name") String first_name,
			  @JsonProperty("last_name") String last_name,
			  @JsonProperty("address") String address,
			  @JsonProperty("city") String city,
			  @JsonProperty("country") String country,
			  @JsonProperty("phone_number") String phone_number,
			  @JsonProperty("unique_num") String unique_num) {
		super();
		this.email = email;
		this.password = password;
		this.first_name = first_name;
		this.last_name = last_name;
		this.address = address;
		this.city = city;
		this.country = country;
		this.phone_number = phone_number;
		this.unique_num = unique_num;
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

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
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

	public String getUnique_num() {
		return unique_num;
	}

	public void setUnique_num(String unique_num) {
		this.unique_num = unique_num;
	}
	
	

}
