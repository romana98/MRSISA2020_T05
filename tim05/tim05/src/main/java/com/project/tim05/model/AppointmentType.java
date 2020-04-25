package com.project.tim05.model;

import javax.persistence.*;

@Entity
@Table(name="appointment_types")
public class AppointmentType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "appointment_type_id", unique=true, nullable = false)
	private Integer id;
	
	@Column(name = "name", nullable = false)
	private String name;

	public AppointmentType() {
		super();
	}

	public AppointmentType(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
