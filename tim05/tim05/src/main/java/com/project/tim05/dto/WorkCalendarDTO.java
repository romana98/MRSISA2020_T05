package com.project.tim05.dto;

import java.util.Date;


public class WorkCalendarDTO {
	
	private String startTime;

	private String endTime;

	private Date date;

	Boolean leave;

	boolean request;

	
	
	
	public WorkCalendarDTO() {
		super();
	}

	public WorkCalendarDTO(String startTime, String endTime, Date date, Boolean leave, boolean request) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.date = date;
		this.leave = leave;
		this.request = request;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
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

	public boolean isRequest() {
		return request;
	}

	public void setRequest(boolean request) {
		this.request = request;
	}
	
	

}
