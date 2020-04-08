package com.project.tim05.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AdminClinic {
	public String name;
	public String surname;
	public String username;
	public String password;
	
	
	
	public AdminClinic(@JsonProperty("name")String name, 
					   @JsonProperty("surname")String surname, 
					   @JsonProperty("username")String username, 
					   @JsonProperty("password")String password) {
		super();
		this.name = name;
		this.surname = surname;
		this.username = username;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
