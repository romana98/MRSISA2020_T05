package com.project.tim05.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class AdminClinic {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name", nullable = false)
	public String name;
	
	@Column(name = "surname", nullable = false)
	public String surname;
	
	@Column(name = "username", nullable = false)
	public String username;
	
	@Column(name = "password", nullable = false)
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
