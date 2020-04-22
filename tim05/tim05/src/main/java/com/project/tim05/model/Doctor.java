package com.project.tim05.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name", nullable = false) 
	private String name;
	
	@Column( name = "surname", nullable = false)
	private String surname;
	
	@Column( name = "username", nullable = false)
	private String username;
	
	@Column( name = "password", nullable = false)
	private String password;
	
	@Column( name = "work_start", nullable = false)
	private String work_start;
	
	@Column( name = "work_end", nullable = false)
	private String work_end;
	
	@Column(length = 15)
	private String type;
	
	public Doctor() {
		super();
	}
	
	public Doctor(@JsonProperty("name") String name,
				  @JsonProperty("surname") String surname, 
				  @JsonProperty("username") String username, 
				  @JsonProperty("password") String password, 
				  @JsonProperty("work_start") String work_start, 
				  @JsonProperty("work_end") String work_end,
				  @JsonProperty("type")	String type) {
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	 
	
	
	
}
