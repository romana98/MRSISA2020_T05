package com.project.tim05.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="leave_requests")
public class LeaveRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "leave_req_id", unique=true, nullable = false)
	private Integer id;
	
	@Column(name = "start_date", nullable = false)
	Date startDate;
	
	@Column(name = "end_date", nullable = false)
	Date endDate;
	
	@Column(name = "email", nullable = false)
	String email;
	
	@Column(name = "name", nullable = false)
	String name;
	
	@Column(name = "surname", nullable = false)
	String surname;
	
	public LeaveRequest() {
		super();
	}
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
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


}
