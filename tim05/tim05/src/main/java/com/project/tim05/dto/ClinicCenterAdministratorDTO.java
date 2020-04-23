package com.project.tim05.dto;

import java.util.*;

public class ClinicCenterAdministratorDTO {
   private String name;
   private String email;
   private String password;
   private String surname;
   
   private ArrayList<ClinicDTO> clinics;
   private ArrayList<DiagnosisDTO> diagnosises;
   private ArrayList<Medicine> medicines;
   private ArrayList<RegistrationRequest> registrationRequests;
   
   
   public ClinicCenterAdministratorDTO() {
	super();
	this.clinics = new ArrayList<>();
	this.diagnosises = new ArrayList<>();
	this.medicines = new ArrayList<>();
	this.registrationRequests = new ArrayList<>();
}

public ClinicCenterAdministratorDTO(String name, String email, String password, String surname,
		ArrayList<ClinicDTO> clinics, ArrayList<DiagnosisDTO> diagnosises, ArrayList<Medicine> medicines,
		ArrayList<RegistrationRequest> registrationRequests) {
	super();
	this.name = name;
	this.email = email;
	this.password = password;
	this.surname = surname;
	this.clinics = clinics;
	this.diagnosises = diagnosises;
	this.medicines = medicines;
	this.registrationRequests = registrationRequests;
}

public ArrayList<ClinicDTO> getClinic() {
      if (clinics == null)
         clinics = new ArrayList<ClinicDTO>();
      return clinics;
   }
   
   public Iterator<ClinicDTO> getIteratorClinic() {
      if (clinics == null)
         clinics = new ArrayList<ClinicDTO>();
      return clinics.iterator();
   }
   
   public void setClinic(ArrayList<ClinicDTO> newClinic) {
      removeAllClinic();
      for (Iterator<ClinicDTO> iter = newClinic.iterator(); iter.hasNext();)
         addClinic((ClinicDTO)iter.next());
   }
   
   public void addClinic(ClinicDTO newClinic) {
      if (newClinic == null)
         return;
      if (this.clinics == null)
         this.clinics = new ArrayList<ClinicDTO>();
      if (!this.clinics.contains(newClinic))
         this.clinics.add(newClinic);
   }
   
   public void removeClinic(ClinicDTO oldClinic) {
      if (oldClinic == null)
         return;
      if (this.clinics != null)
         if (this.clinics.contains(oldClinic))
            this.clinics.remove(oldClinic);
   }
   
   public void removeAllClinic() {
      if (clinics != null)
         clinics.clear();
   }
  
   public ArrayList<DiagnosisDTO> getDiagnosis() {
      if (diagnosises == null)
         diagnosises = new ArrayList<DiagnosisDTO>();
      return diagnosises;
   }
   
   public Iterator<DiagnosisDTO> getIteratorDiagnosis() {
      if (diagnosises == null)
         diagnosises = new ArrayList<DiagnosisDTO>();
      return diagnosises.iterator();
   }
   
   public void setDiagnosis(ArrayList<DiagnosisDTO> newDiagnosis) {
      removeAllDiagnosis();
      for (Iterator<DiagnosisDTO> iter = newDiagnosis.iterator(); iter.hasNext();)
         addDiagnosis((DiagnosisDTO)iter.next());
   }
   
   public void addDiagnosis(DiagnosisDTO newDiagnosis) {
      if (newDiagnosis == null)
         return;
      if (this.diagnosises == null)
         this.diagnosises = new ArrayList<DiagnosisDTO>();
      if (!this.diagnosises.contains(newDiagnosis))
         this.diagnosises.add(newDiagnosis);
   }
   
   public void removeDiagnosis(DiagnosisDTO oldDiagnosis) {
      if (oldDiagnosis == null)
         return;
      if (this.diagnosises != null)
         if (this.diagnosises.contains(oldDiagnosis))
            this.diagnosises.remove(oldDiagnosis);
   }
   
   public void removeAllDiagnosis() {
      if (diagnosises != null)
         diagnosises.clear();
   }
  
   public ArrayList<Medicine> getMedicine() {
      if (medicines == null)
         medicines = new ArrayList<Medicine>();
      return medicines;
   }
   
   public Iterator<Medicine> getIteratorMedicine() {
      if (medicines == null)
         medicines = new ArrayList<Medicine>();
      return medicines.iterator();
   }
   
   public void setMedicine(ArrayList<Medicine> newMedicine) {
      removeAllMedicine();
      for (Iterator<Medicine> iter = newMedicine.iterator(); iter.hasNext();)
         addMedicine((Medicine)iter.next());
   }
   
   public void addMedicine(Medicine newMedicine) {
      if (newMedicine == null)
         return;
      if (this.medicines == null)
         this.medicines = new ArrayList<Medicine>();
      if (!this.medicines.contains(newMedicine))
         this.medicines.add(newMedicine);
   }
   
   public void removeMedicine(Medicine oldMedicine) {
      if (oldMedicine == null)
         return;
      if (this.medicines != null)
         if (this.medicines.contains(oldMedicine))
            this.medicines.remove(oldMedicine);
   }
   
   public void removeAllMedicine() {
      if (medicines != null)
         medicines.clear();
   }
   
   public ArrayList<RegistrationRequest> getRegistrationRequest() {
      if (registrationRequests == null)
         registrationRequests = new ArrayList<RegistrationRequest>();
      return registrationRequests;
   }
   
   public Iterator<RegistrationRequest> getIteratorRegistrationRequest() {
      if (registrationRequests == null)
         registrationRequests = new ArrayList<RegistrationRequest>();
      return registrationRequests.iterator();
   }
   
   public void setRegistrationRequest(ArrayList<RegistrationRequest> newRegistrationRequest) {
      removeAllRegistrationRequest();
      for (Iterator<RegistrationRequest> iter = newRegistrationRequest.iterator(); iter.hasNext();)
         addRegistrationRequest((RegistrationRequest)iter.next());
   }
   
   public void addRegistrationRequest(RegistrationRequest newRegistrationRequest) {
      if (newRegistrationRequest == null)
         return;
      if (this.registrationRequests == null)
         this.registrationRequests = new ArrayList<RegistrationRequest>();
      if (!this.registrationRequests.contains(newRegistrationRequest))
         this.registrationRequests.add(newRegistrationRequest);
   }
   
   public void removeRegistrationRequest(RegistrationRequest oldRegistrationRequest) {
      if (oldRegistrationRequest == null)
         return;
      if (this.registrationRequests != null)
         if (this.registrationRequests.contains(oldRegistrationRequest))
            this.registrationRequests.remove(oldRegistrationRequest);
   }
   
   public void removeAllRegistrationRequest() {
      if (registrationRequests != null)
         registrationRequests.clear();
   }

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
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

public String getSurname() {
	return surname;
}

public void setSurname(String surname) {
	this.surname = surname;
}
   
   

}