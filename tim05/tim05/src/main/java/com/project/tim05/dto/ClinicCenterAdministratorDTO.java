package com.project.tim05.dto;

import java.util.*;

public class ClinicCenterAdministratorDTO {
   private String name;
   private String email;
   private String password;
   private String surname;
   
   private ArrayList<ClinicDTO> clinics;
   private ArrayList<DiagnosisDTO> diagnosises;
   private ArrayList<MedicineDTO> medicines;
   private ArrayList<RegistrationRequestDTO> registrationRequests;
   
   
   public ClinicCenterAdministratorDTO() {
	super();
	this.clinics = new ArrayList<>();
	this.diagnosises = new ArrayList<>();
	this.medicines = new ArrayList<>();
	this.registrationRequests = new ArrayList<>();
}

public ClinicCenterAdministratorDTO(String name, String email, String password, String surname,
		ArrayList<ClinicDTO> clinics, ArrayList<DiagnosisDTO> diagnosises, ArrayList<MedicineDTO> medicines,
		ArrayList<RegistrationRequestDTO> registrationRequests) {
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
  
   public ArrayList<MedicineDTO> getMedicine() {
      if (medicines == null)
         medicines = new ArrayList<MedicineDTO>();
      return medicines;
   }
   
   public Iterator<MedicineDTO> getIteratorMedicine() {
      if (medicines == null)
         medicines = new ArrayList<MedicineDTO>();
      return medicines.iterator();
   }
   
   public void setMedicine(ArrayList<MedicineDTO> newMedicine) {
      removeAllMedicine();
      for (Iterator<MedicineDTO> iter = newMedicine.iterator(); iter.hasNext();)
         addMedicine((MedicineDTO)iter.next());
   }
   
   public void addMedicine(MedicineDTO newMedicine) {
      if (newMedicine == null)
         return;
      if (this.medicines == null)
         this.medicines = new ArrayList<MedicineDTO>();
      if (!this.medicines.contains(newMedicine))
         this.medicines.add(newMedicine);
   }
   
   public void removeMedicine(MedicineDTO oldMedicine) {
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
   
   public ArrayList<RegistrationRequestDTO> getRegistrationRequest() {
      if (registrationRequests == null)
         registrationRequests = new ArrayList<RegistrationRequestDTO>();
      return registrationRequests;
   }
   
   public Iterator<RegistrationRequestDTO> getIteratorRegistrationRequest() {
      if (registrationRequests == null)
         registrationRequests = new ArrayList<RegistrationRequestDTO>();
      return registrationRequests.iterator();
   }
   
   public void setRegistrationRequest(ArrayList<RegistrationRequestDTO> newRegistrationRequest) {
      removeAllRegistrationRequest();
      for (Iterator<RegistrationRequestDTO> iter = newRegistrationRequest.iterator(); iter.hasNext();)
         addRegistrationRequest((RegistrationRequestDTO)iter.next());
   }
   
   public void addRegistrationRequest(RegistrationRequestDTO newRegistrationRequest) {
      if (newRegistrationRequest == null)
         return;
      if (this.registrationRequests == null)
         this.registrationRequests = new ArrayList<RegistrationRequestDTO>();
      if (!this.registrationRequests.contains(newRegistrationRequest))
         this.registrationRequests.add(newRegistrationRequest);
   }
   
   public void removeRegistrationRequest(RegistrationRequestDTO oldRegistrationRequest) {
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