package com.project.tim05.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class LeaveRequestDTO {
	
	@NotNull
	@NotBlank
	private String startDate;
	
	@NotNull
	@NotBlank
	private String endDate;
	
	private String email;
	
	private String name;
	
	private String surname;
	
	private String text;
	

	public LeaveRequestDTO() {
		super();
	}

	public LeaveRequestDTO(String email, String name, String surname, String startDate, String endDate) {
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	

}
