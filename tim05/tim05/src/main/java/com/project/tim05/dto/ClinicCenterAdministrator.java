package com.project.tim05.dto;
/***********************************************************************
 * Module:  ClinicCenterAdministrator.java
 * Author:  Vukasin
 * Purpose: Defines the Class ClinicCenterAdministrator
 ***********************************************************************/

import java.util.*;

/** @pdOid 42c8823a-95b0-4b1f-92a2-516ee79fe35a */
public class ClinicCenterAdministrator {
   /** @pdOid 3403c593-6bc7-4957-8778-bfcf622b79b5 */
   private String name;
   /** @pdOid 55cb47da-a074-4c4b-8d4d-6d6c18bf5f76 */
   private String email;
   /** @pdOid 88272c7c-7176-4957-bf01-f46dc3341d6d */
   private String password;
   /** @pdOid 0c4d6632-6906-461b-a833-f36ed3cbc924 */
   private String surname;
   
   /** @pdRoleInfo migr=no name=Clinic assc=association14 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Clinic> clinic;
   /** @pdRoleInfo migr=no name=Diagnosis assc=association15 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Diagnosis> diagnosis;
   /** @pdRoleInfo migr=no name=Medicine assc=association16 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Medicine> medicine;
   /** @pdRoleInfo migr=no name=RegistrationRequest assc=association22 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<RegistrationRequest> registrationRequest;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<Clinic> getClinic() {
      if (clinic == null)
         clinic = new java.util.HashSet<Clinic>();
      return clinic;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorClinic() {
      if (clinic == null)
         clinic = new java.util.HashSet<Clinic>();
      return clinic.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newClinic */
   public void setClinic(java.util.Collection<Clinic> newClinic) {
      removeAllClinic();
      for (java.util.Iterator iter = newClinic.iterator(); iter.hasNext();)
         addClinic((Clinic)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newClinic */
   public void addClinic(Clinic newClinic) {
      if (newClinic == null)
         return;
      if (this.clinic == null)
         this.clinic = new java.util.HashSet<Clinic>();
      if (!this.clinic.contains(newClinic))
         this.clinic.add(newClinic);
   }
   
   /** @pdGenerated default remove
     * @param oldClinic */
   public void removeClinic(Clinic oldClinic) {
      if (oldClinic == null)
         return;
      if (this.clinic != null)
         if (this.clinic.contains(oldClinic))
            this.clinic.remove(oldClinic);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllClinic() {
      if (clinic != null)
         clinic.clear();
   }
   /** @pdGenerated default getter */
   public java.util.Collection<Diagnosis> getDiagnosis() {
      if (diagnosis == null)
         diagnosis = new java.util.HashSet<Diagnosis>();
      return diagnosis;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorDiagnosis() {
      if (diagnosis == null)
         diagnosis = new java.util.HashSet<Diagnosis>();
      return diagnosis.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newDiagnosis */
   public void setDiagnosis(java.util.Collection<Diagnosis> newDiagnosis) {
      removeAllDiagnosis();
      for (java.util.Iterator iter = newDiagnosis.iterator(); iter.hasNext();)
         addDiagnosis((Diagnosis)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newDiagnosis */
   public void addDiagnosis(Diagnosis newDiagnosis) {
      if (newDiagnosis == null)
         return;
      if (this.diagnosis == null)
         this.diagnosis = new java.util.HashSet<Diagnosis>();
      if (!this.diagnosis.contains(newDiagnosis))
         this.diagnosis.add(newDiagnosis);
   }
   
   /** @pdGenerated default remove
     * @param oldDiagnosis */
   public void removeDiagnosis(Diagnosis oldDiagnosis) {
      if (oldDiagnosis == null)
         return;
      if (this.diagnosis != null)
         if (this.diagnosis.contains(oldDiagnosis))
            this.diagnosis.remove(oldDiagnosis);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllDiagnosis() {
      if (diagnosis != null)
         diagnosis.clear();
   }
   /** @pdGenerated default getter */
   public java.util.Collection<Medicine> getMedicine() {
      if (medicine == null)
         medicine = new java.util.HashSet<Medicine>();
      return medicine;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorMedicine() {
      if (medicine == null)
         medicine = new java.util.HashSet<Medicine>();
      return medicine.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newMedicine */
   public void setMedicine(java.util.Collection<Medicine> newMedicine) {
      removeAllMedicine();
      for (java.util.Iterator iter = newMedicine.iterator(); iter.hasNext();)
         addMedicine((Medicine)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newMedicine */
   public void addMedicine(Medicine newMedicine) {
      if (newMedicine == null)
         return;
      if (this.medicine == null)
         this.medicine = new java.util.HashSet<Medicine>();
      if (!this.medicine.contains(newMedicine))
         this.medicine.add(newMedicine);
   }
   
   /** @pdGenerated default remove
     * @param oldMedicine */
   public void removeMedicine(Medicine oldMedicine) {
      if (oldMedicine == null)
         return;
      if (this.medicine != null)
         if (this.medicine.contains(oldMedicine))
            this.medicine.remove(oldMedicine);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllMedicine() {
      if (medicine != null)
         medicine.clear();
   }
   /** @pdGenerated default getter */
   public java.util.Collection<RegistrationRequest> getRegistrationRequest() {
      if (registrationRequest == null)
         registrationRequest = new java.util.HashSet<RegistrationRequest>();
      return registrationRequest;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorRegistrationRequest() {
      if (registrationRequest == null)
         registrationRequest = new java.util.HashSet<RegistrationRequest>();
      return registrationRequest.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newRegistrationRequest */
   public void setRegistrationRequest(java.util.Collection<RegistrationRequest> newRegistrationRequest) {
      removeAllRegistrationRequest();
      for (java.util.Iterator iter = newRegistrationRequest.iterator(); iter.hasNext();)
         addRegistrationRequest((RegistrationRequest)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newRegistrationRequest */
   public void addRegistrationRequest(RegistrationRequest newRegistrationRequest) {
      if (newRegistrationRequest == null)
         return;
      if (this.registrationRequest == null)
         this.registrationRequest = new java.util.HashSet<RegistrationRequest>();
      if (!this.registrationRequest.contains(newRegistrationRequest))
         this.registrationRequest.add(newRegistrationRequest);
   }
   
   /** @pdGenerated default remove
     * @param oldRegistrationRequest */
   public void removeRegistrationRequest(RegistrationRequest oldRegistrationRequest) {
      if (oldRegistrationRequest == null)
         return;
      if (this.registrationRequest != null)
         if (this.registrationRequest.contains(oldRegistrationRequest))
            this.registrationRequest.remove(oldRegistrationRequest);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllRegistrationRequest() {
      if (registrationRequest != null)
         registrationRequest.clear();
   }

}