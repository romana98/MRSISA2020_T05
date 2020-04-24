package com.project.tim05.dto;

import java.util.*;

public class ClinicCenterAdministratorDTO {
   private String name;
   private String email;
   private String password;
   private String surname;
   
   private Set<ClinicDTO> clinics;
   private Set<DiagnosisDTO> diagnosises;
   private Set<MedicineDTO> medicines;
   private Set<RegistrationRequestDTO> registrationRequests;
   
   
   public ClinicCenterAdministratorDTO() {
	super();
	this.clinics = new HashSet<>();
	this.diagnosises = new HashSet<>();
	this.medicines = new HashSet<>();
	this.registrationRequests = new HashSet<>();
}

public ClinicCenterAdministratorDTO(String name, String email, String password, String surname,
		HashSet<ClinicDTO> clinics, HashSet<DiagnosisDTO> diagnosises, HashSet<MedicineDTO> medicines,
		HashSet<RegistrationRequestDTO> registrationRequests) {
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

public Set<ClinicDTO> getClinic() {
      if (clinics == null)
         clinics = new HashSet<ClinicDTO>();
      return clinics;
   }
   
   public Iterator<ClinicDTO> getIteratorClinic() {
      if (clinics == null)
         clinics = new HashSet<ClinicDTO>();
      return clinics.iterator();
   }
   
   public void setClinic(HashSet<ClinicDTO> newClinic) {
      removeAllClinic();
      for (Iterator<ClinicDTO> iter = newClinic.iterator(); iter.hasNext();)
         addClinic((ClinicDTO)iter.next());
   }
   
   public void addClinic(ClinicDTO newClinic) {
      if (newClinic == null)
         return;
      if (this.clinics == null)
         this.clinics = new HashSet<ClinicDTO>();
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
  
   public Set<DiagnosisDTO> getDiagnosis() {
      if (diagnosises == null)
         diagnosises = new HashSet<DiagnosisDTO>();
      return diagnosises;
   }
   
   public Iterator<DiagnosisDTO> getIteratorDiagnosis() {
      if (diagnosises == null)
         diagnosises = new HashSet<DiagnosisDTO>();
      return diagnosises.iterator();
   }
   
   public void setDiagnosis(HashSet<DiagnosisDTO> newDiagnosis) {
      removeAllDiagnosis();
      for (Iterator<DiagnosisDTO> iter = newDiagnosis.iterator(); iter.hasNext();)
         addDiagnosis((DiagnosisDTO)iter.next());
   }
   
   public void addDiagnosis(DiagnosisDTO newDiagnosis) {
      if (newDiagnosis == null)
         return;
      if (this.diagnosises == null)
         this.diagnosises = new HashSet<DiagnosisDTO>();
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
  
   public Set<MedicineDTO> getMedicine() {
      if (medicines == null)
         medicines = new HashSet<MedicineDTO>();
      return medicines;
   }
   
   public Iterator<MedicineDTO> getIteratorMedicine() {
      if (medicines == null)
         medicines = new HashSet<MedicineDTO>();
      return medicines.iterator();
   }
   
   public void setMedicine(HashSet<MedicineDTO> newMedicine) {
      removeAllMedicine();
      for (Iterator<MedicineDTO> iter = newMedicine.iterator(); iter.hasNext();)
         addMedicine((MedicineDTO)iter.next());
   }
   
   public void addMedicine(MedicineDTO newMedicine) {
      if (newMedicine == null)
         return;
      if (this.medicines == null)
         this.medicines = new HashSet<MedicineDTO>();
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
   
   public Set<RegistrationRequestDTO> getRegistrationRequest() {
      if (registrationRequests == null)
         registrationRequests = new HashSet<RegistrationRequestDTO>();
      return registrationRequests;
   }
   
   public Iterator<RegistrationRequestDTO> getIteratorRegistrationRequest() {
      if (registrationRequests == null)
         registrationRequests = new HashSet<RegistrationRequestDTO>();
      return registrationRequests.iterator();
   }
   
   public void setRegistrationRequest(HashSet<RegistrationRequestDTO> newRegistrationRequest) {
      removeAllRegistrationRequest();
      for (Iterator<RegistrationRequestDTO> iter = newRegistrationRequest.iterator(); iter.hasNext();)
         addRegistrationRequest((RegistrationRequestDTO)iter.next());
   }
   
   public void addRegistrationRequest(RegistrationRequestDTO newRegistrationRequest) {
      if (newRegistrationRequest == null)
         return;
      if (this.registrationRequests == null)
         this.registrationRequests = new HashSet<RegistrationRequestDTO>();
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