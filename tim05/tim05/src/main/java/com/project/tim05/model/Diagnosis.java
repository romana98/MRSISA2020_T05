package com.project.tim05.model;

import javax.persistence.*;


@Entity
@Table(name="diagnosises")
public class Diagnosis {
   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "diagnosis_id", unique=true, nullable = false)
	private Integer id;
	
	@Column(name = "name", nullable = false)
	private String name;
   
	@Column(name = "description", nullable = false)
	private String description;
	

	public Diagnosis() {
		super();
		
	}

	public Diagnosis(Integer id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
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
	
	

}