package com.project.tim05.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Doctor {

	private String name;
	private String surname;
	private String username;
	private String password;
	private String work_start;
	private String work_end;
	private AppointmentType type;
	
	public Doctor(@JsonProperty("name") String name,
				  @JsonProperty("surname") String surname, 
				  @JsonProperty("username") String username, 
				  @JsonProperty("password") String password, 
				  @JsonProperty("work_start") String work_start, 
				  @JsonProperty("work_end") String work_end,
				  @JsonProperty("type")	AppointmentType type) {
		super();
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.password = password;
		this.work_start = work_start;
		this.work_end = work_end;
		this.type = type;
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
	public String getWork_start() {
		return work_start;
	}
	public void setWork_start(String work_start) {
		this.work_start = work_start;
	}
	public String getWork_end() {
		return work_end;
	}
	public void setWork_end(String work_end) {
		this.work_end = work_end;
	}
	public AppointmentType getType() {
		return type;
	}
	public void setType(AppointmentType type) {
		this.type = type;
	}
	 
	
	
	
}
