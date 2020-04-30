package com.project.tim05.model;

import java.util.Set;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class MedicalStaff {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "staff_id", unique=true, nullable = false)
	private Integer id;
  
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "surname", nullable = false)
	private String surname;
	
	@Column(name = "email", nullable = false, unique= true)
	private String email;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "workStart", nullable = false)
	private String workStart;
	
	@Column(name = "workEnd", nullable = false)
	private String workEnd;
	
	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
	private Set<Patient> patients;
	
	public MedicalStaff() {
		super();
	}
	

	public MedicalStaff(String name, String surname, String email, String password, String workStart, String workEnd,
			Set<Patient> patients) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.workStart = workStart;
		this.workEnd = workEnd;
		this.patients = patients;
	}

		


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Set<Patient> getPatients() {
		return patients;
	}



	public void setPatients(Set<Patient> patients) {
		this.patients = patients;
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
	
	
}