package com.project.tim05.model;

import java.util.*;

import javax.persistence.*;

@Entity
@Table(name="nurses")
public class Nurse extends MedicalStaff {
	
	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="nurse")
	private Set<Medicine> medicines = new HashSet<Medicine>();
   
	public Nurse() {
		super();
	}

	public Set<Medicine> getMedicines() {
		return medicines;
	}

	public void setMedicines(Set<Medicine> medicines) {
		this.medicines = medicines;
	}

	
	
}