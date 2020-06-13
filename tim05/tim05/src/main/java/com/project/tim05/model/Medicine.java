package com.project.tim05.model;

import javax.persistence.*;

@Entity
@Table(name="medicines", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "description"})})
public class Medicine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "medicine_id", unique=true, nullable = false)
	private Integer id;
	
	@Column(name = "name", nullable = false, unique = true)
	private String name;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@OneToOne(cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
	@JoinColumn(name="appointment_medicine")
	private AppointmentMedicine appointmentMedicine;
	
		
	public Medicine() {
		super();
	}
	
	

	
	public Medicine(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}




	public Medicine(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}




	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	

	public String getDescription() {
		return description;
	}




	public void setDescription(String description) {
		this.description = description;
	}



	public AppointmentMedicine getAppointmentMedicine() {
		return appointmentMedicine;
	}




	public void setAppointmentMedicine(AppointmentMedicine appointmentMedicine) {
		this.appointmentMedicine = appointmentMedicine;
	}




	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
}