package com.project.tim05.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "work_calendars")
public class WorkCalendar {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "work_cal_id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "start_time", nullable = false)
	private String startTime;

	@Column(name = "end_time", nullable = false)
	private String endTime;

	@Column(name = "date", nullable = false)
	private Date date;

	@Column(name = "leave", nullable = false)
	Boolean leave;

	@Column(name = "request", nullable = false)
	boolean request;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "doctor", referencedColumnName = "user_id", nullable = true)
	private Doctor doctor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "nurse", referencedColumnName = "user_id", nullable = true)
	private Nurse nurse;

	public WorkCalendar() {
		super();
	}

	public WorkCalendar(String startTime, String endTime, Date date, Boolean leave) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.date = date;
		this.leave = leave;
		this.doctor = doctor;
		this.nurse = nurse;
	}

	public boolean isRequest() {
		return request;
	}

	public void setRequest(boolean request) {
		this.request = request;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStart_time() {
		return startTime;
	}

	public void setStart_time(String start_time) {
		this.startTime = start_time;
	}

	public String getEnd_time() {
		return endTime;
	}

	public void setEnd_time(String end_time) {
		this.endTime = end_time;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Boolean getLeave() {
		return leave;
	}

	public void setLeave(Boolean leave) {
		this.leave = leave;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Nurse getNurse() {
		return nurse;
	}

	public void setNurse(Nurse nurse) {
		this.nurse = nurse;
	}

}
