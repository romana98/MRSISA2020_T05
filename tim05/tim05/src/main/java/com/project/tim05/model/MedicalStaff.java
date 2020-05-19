package com.project.tim05.model;

import java.util.Set;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class MedicalStaff extends User {

	private static final long serialVersionUID = 1L;

	@Column(name = "workStart", nullable = false)
	private String workStart;
	
	@Column(name = "workEnd", nullable = false)
	private String workEnd;
	
	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
	private Set<Patient> patients;
	
	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
	private Set<WorkCalendar> workCalendar;
	
	public MedicalStaff() {
		super();
	}


	public Set<Patient> getPatients() {
		return patients;
	}



	public void setPatients(Set<Patient> patients) {
		this.patients = patients;
	}

	public String getWorkStart() {
		return workStart;
	}

	public void setWorkStart(String workStart) {
		this.workStart = workStart;
	}

	public String getWorkEnd() {
		return workEnd;
	}

	public void setWorkEnd(String workEnd) {
		this.workEnd = workEnd;
	}


	public Set<WorkCalendar> getWorkCalendar() {
		return workCalendar;
	}


	public void setWorkCalendar(Set<WorkCalendar> workCalendar) {
		this.workCalendar = workCalendar;
	}
	
	
	
}