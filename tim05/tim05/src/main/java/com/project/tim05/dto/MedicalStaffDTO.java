package com.project.tim05.dto;

import java.util.*;

public class MedicalStaffDTO {
   
   private String name;
   private String surname;
   private String email;
   private String password;
   private String workStart;
   private String workEnd;
   
   private ArrayList<PatientDTO> patients;
   
   
   public MedicalStaffDTO() {
	super();
}

public MedicalStaffDTO(String name, String surname, String email, String password, String workStart, String workEnd,
		ArrayList<PatientDTO> patients) {
	super();
	this.name = name;
	this.surname = surname;
	this.email = email;
	this.password = password;
	this.workStart = workStart;
	this.workEnd = workEnd;
	this.patients = patients;
}

public ArrayList<PatientDTO> getPatient() {
      if (patients == null)
         patients = new ArrayList<PatientDTO>();
      return patients;
   }
   
   public Iterator<PatientDTO> getIteratorPatient() {
      if (patients == null)
         patients = new ArrayList<PatientDTO>();
      return patients.iterator();
   }
   
   public void setPatient(ArrayList<PatientDTO> newPatient) {
      removeAllPatient();
      for (Iterator<PatientDTO> iter = newPatient.iterator(); iter.hasNext();)
         addPatient((PatientDTO)iter.next());
   }
   
   public void addPatient(PatientDTO newPatient) {
      if (newPatient == null)
         return;
      if (this.patients == null)
         this.patients = new ArrayList<PatientDTO>();
      if (!this.patients.contains(newPatient))
         this.patients.add(newPatient);
   }
   
   public void removePatient(PatientDTO oldPatient) {
      if (oldPatient == null)
         return;
      if (this.patients != null)
         if (this.patients.contains(oldPatient))
            this.patients.remove(oldPatient);
   }
   
   public void removeAllPatient() {
      if (patients != null)
         patients.clear();
   }

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getSurname() {
	return surname;
}

public void setSurname(String surname) {
	this.surname = surname;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getWorkStart() {
	return workStart;
}

public void setWorkStart(String workStart) {
	this.workStart = workStart;
}

public String getWorkEnd() {
	return workEnd;
}

public void setWorkEnd(String workEnd) {
	this.workEnd = workEnd;
}

public ArrayList<PatientDTO> getPatients() {
	return patients;
}

public void setPatients(ArrayList<PatientDTO> patients) {
	this.patients = patients;
}
   
   
}