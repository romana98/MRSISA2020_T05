package com.project.tim05.dto;

import java.util.*;

public class MedicalStaffDTO {
   
   private String name;
   private String surname;
   private String email;
   private String password;
   private String workStart;
   private String workEnd;
   
   public ArrayList<PatientDTO> patient;
   
   
   public MedicalStaffDTO() {
	super();
}

public MedicalStaffDTO(String name, String surname, String email, String password, String workStart, String workEnd,
		ArrayList<PatientDTO> patient) {
	super();
	this.name = name;
	this.surname = surname;
	this.email = email;
	this.password = password;
	this.workStart = workStart;
	this.workEnd = workEnd;
	this.patient = patient;
}

public ArrayList<PatientDTO> getPatient() {
      if (patient == null)
         patient = new ArrayList<PatientDTO>();
      return patient;
   }
   
   public Iterator<PatientDTO> getIteratorPatient() {
      if (patient == null)
         patient = new ArrayList<PatientDTO>();
      return patient.iterator();
   }
   
   public void setPatient(ArrayList<PatientDTO> newPatient) {
      removeAllPatient();
      for (Iterator<PatientDTO> iter = newPatient.iterator(); iter.hasNext();)
         addPatient((PatientDTO)iter.next());
   }
   
   public void addPatient(PatientDTO newPatient) {
      if (newPatient == null)
         return;
      if (this.patient == null)
         this.patient = new ArrayList<PatientDTO>();
      if (!this.patient.contains(newPatient))
         this.patient.add(newPatient);
   }
   
   public void removePatient(PatientDTO oldPatient) {
      if (oldPatient == null)
         return;
      if (this.patient != null)
         if (this.patient.contains(oldPatient))
            this.patient.remove(oldPatient);
   }
   
   public void removeAllPatient() {
      if (patient != null)
         patient.clear();
   }

}