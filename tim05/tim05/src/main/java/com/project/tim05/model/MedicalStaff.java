package com.project.tim05.model;
/***********************************************************************
 * Module:  MedicalStaff.java
 * Author:  Vukasin
 * Purpose: Defines the Class MedicalStaff
 ***********************************************************************/

import java.util.*;

/** @pdOid 7c811631-4a5c-46b0-85ae-3127a31e7b28 */
public class MedicalStaff {
   /** @pdOid 212f78cb-f82d-49b6-bc5b-64fb33294f65 */
   private String name;
   /** @pdOid 0cfaf878-83e3-40f9-8f06-104ba3061d8c */
   private String surname;
   /** @pdOid fc71f71f-ffc3-4290-a2a0-9d634102627e */
   private String email;
   /** @pdOid c9dfad1f-cc6c-4ed2-90ea-f31690a84502 */
   private String password;
   /** @pdOid 3ce7c571-89f0-4063-9345-57657c7fefd3 */
   private String workStart;
   /** @pdOid 2783c294-f4bb-4700-9817-f48ff05a80fc */
   private String workEnd;
   
   /** @pdRoleInfo migr=no name=Patient assc=association26 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Patient> patient;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<Patient> getPatient() {
      if (patient == null)
         patient = new java.util.HashSet<Patient>();
      return patient;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorPatient() {
      if (patient == null)
         patient = new java.util.HashSet<Patient>();
      return patient.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newPatient */
   public void setPatient(java.util.Collection<Patient> newPatient) {
      removeAllPatient();
      for (java.util.Iterator iter = newPatient.iterator(); iter.hasNext();)
         addPatient((Patient)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newPatient */
   public void addPatient(Patient newPatient) {
      if (newPatient == null)
         return;
      if (this.patient == null)
         this.patient = new java.util.HashSet<Patient>();
      if (!this.patient.contains(newPatient))
         this.patient.add(newPatient);
   }
   
   /** @pdGenerated default remove
     * @param oldPatient */
   public void removePatient(Patient oldPatient) {
      if (oldPatient == null)
         return;
      if (this.patient != null)
         if (this.patient.contains(oldPatient))
            this.patient.remove(oldPatient);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllPatient() {
      if (patient != null)
         patient.clear();
   }

}