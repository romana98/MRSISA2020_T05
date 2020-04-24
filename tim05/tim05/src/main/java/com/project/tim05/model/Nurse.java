package com.project.tim05.model;

import java.util.*;

import javax.persistence.*;

@Entity
@Table(name="nurses")
public class Nurse extends MedicalStaff {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "nurse_id", unique=true, nullable = false)
	private Integer id;
	
	@OneToMany(cascade = {CascadeType.ALL}, fetch=FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "medicine_id")
	private Set<Medicine> medicines = new HashSet<Medicine>();
   
	public Nurse() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Set<Medicine> getMedicines() {
		return medicines;
	}

	public void setMedicines(Set<Medicine> medicines) {
		this.medicines = medicines;
	}

	
}