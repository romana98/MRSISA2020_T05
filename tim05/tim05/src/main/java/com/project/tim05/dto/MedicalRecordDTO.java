package com.project.tim05.dto;

import java.util.*;

public class MedicalRecordDTO {
	
   private Set<DiseaseDTO> diseases;
   private Set<AppointmentDTO> appointments;
   
   public MedicalRecordDTO(HashSet<DiseaseDTO> diseases, HashSet<AppointmentDTO> appointments) {
	super();
	this.diseases = diseases;
	this.appointments = appointments;
   }
   
   public MedicalRecordDTO() {
	super();
	this.diseases = new HashSet<>();
	this.appointments = new HashSet<>();
   }

public Set<DiseaseDTO> getDiseases() {
	return diseases;
}

public void setDiseases(Set<DiseaseDTO> diseases) {
	this.diseases = diseases;
}

public Set<AppointmentDTO> getAppointments() {
	return appointments;
}

public void setAppointments(Set<AppointmentDTO> appointments) {
	this.appointments = appointments;
}
   
   

}