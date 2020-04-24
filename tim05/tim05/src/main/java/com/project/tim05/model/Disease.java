package com.project.tim05.model;

import javax.persistence.*;

@Entity
@Table(name="diseases")
public class Disease {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "disease_id", unique=true, nullable = false)
	private Integer id;
	
	@Column(name = "name", nullable = false)
	private String name;
  
	@Column(name = "value", nullable = false)
	private String value;
   
	@Column(name = "description", nullable = false)
	private String description;


	public Disease() {
		super();
		
	}

	public Disease(Integer id, String name, String value, String description) {
		super();
		this.id = id;
		this.name = name;
		this.value = value;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}