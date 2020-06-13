package com.project.tim05.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;


@Entity
@Table(name="diagnosises", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "description"})})
public class Diagnosis {
   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "diagnosis_id", unique=true, nullable = false)
	private Integer id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="diagnosis")
	private Set<Appointment> diagnosises = new HashSet<Appointment>();

	public Diagnosis() {
		super();
		
	}
	
	public Diagnosis(String name, String description) {
		this.name = name;
		this.description = description;
		
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Set<Appointment> getDiagnosises() {
		return diagnosises;
	}

	public void setDiagnosises(Set<Appointment> diagnosises) {
		this.diagnosises = diagnosises;
	}

	


}