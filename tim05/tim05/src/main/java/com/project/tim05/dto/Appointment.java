package com.project.tim05.dto;
/***********************************************************************
 * Module:  Appointment.java
 * Author:  Vukasin
 * Purpose: Defines the Class Appointment
 ***********************************************************************/

import java.util.*;

/** @pdOid e81118c9-b2cd-4e3a-9419-d6cc4d7f6fd5 */
public class Appointment {
   /** @pdOid c4db61e9-9362-48c2-a3af-1596f7256ce4 */
   private Date dateTime;
   /** @pdOid 9ecc5aac-a8ad-4a77-9314-b09b132eee76 */
   private int duration;
   /** @pdOid 4cd8b67d-dde4-45cc-9b0a-a1d9ff23bcb2 */
   private double price;
   /** @pdOid bd0eeff3-573d-4e21-990c-e8c56e0af675 */
   private boolean request;
   /** @pdOid a43d80a0-36ee-48de-9460-e991a7956cb7 */
   private boolean finished;
   
   /** @pdRoleInfo migr=no name=Clinic assc=association2 mult=1..1 */
   public Clinic clinic;
   /** @pdRoleInfo migr=no name=Hall assc=association6 mult=0..1 */
   public Hall hall;
   /** @pdRoleInfo migr=no name=Doctor assc=association7 mult=1..1 */
   public Doctor doctor;
   /** @pdRoleInfo migr=no name=AppointmentType assc=association8 mult=1..1 */
   public AppointmentType appointmentType;
   /** @pdRoleInfo migr=no name=Medicine assc=association17 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Medicine> medicine;
   /** @pdRoleInfo migr=no name=Diagnosis assc=association18 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Diagnosis> diagnosis;
   /** @pdRoleInfo migr=no name=Disease assc=association19 mult=0..* */
   public Disease[] disease;
   
   
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

}