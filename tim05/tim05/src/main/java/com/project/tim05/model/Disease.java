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
  
	@Column(name = "value")
	private String value;
   
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="medical_record", referencedColumnName="medical_record_id", nullable=true)
	private MedicalRecord medicalRecord;
   
	

	public Disease() {
		super();
		
	}
	
	public Disease(String name, MedicalRecord mr) {
		super();
		this.name = name;
		this.medicalRecord = mr;
		
	}

	public Disease(Integer id, String name, String value) {
		super();
		this.id = id;
		this.name = name;
		this.value = value;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MedicalRecord getMedicalRecord() {
		return medicalRecord;
	}

	public void setMedicalRecord(MedicalRecord medicalRecord) {
		this.medicalRecord = medicalRecord;
	}
	
	

}