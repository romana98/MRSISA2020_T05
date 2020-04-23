package com.project.tim05.dto;
/***********************************************************************
 * Module:  Patient.java
 * Author:  Vukasin
 * Purpose: Defines the Class Patient
 ***********************************************************************/

import java.util.*;

/** @pdOid afe48b46-d7ca-4539-81a2-5ba27db0ee9a */
public class Patient {
   /** @pdOid 5c204dc1-6f2e-498c-b69b-d12d309e1287 */
   private String email;
   /** @pdOid ea6d9cf0-aef7-4c7d-addd-f19920bff9ce */
   private String password;
   /** @pdOid cb8bf290-c00f-4019-b97a-42dfaed806b2 */
   private String name;
   /** @pdOid e8207929-431f-4165-b173-ece5c32ea2ab */
   private String surname;
   /** @pdOid be3cf8de-6693-477e-b3ba-b8cb2b1c4f91 */
   private String address;
   /** @pdOid 09103e3f-271a-48fc-a56e-5f347640023b */
   private String city;
   /** @pdOid 24e1d80e-029b-450d-9795-2c2a1c3ed6cf */
   private String town;
   /** @pdOid cf1742d9-fe99-4444-90df-f2f4b52a6691 */
   private int number;
   /** @pdOid ab7c87ad-d430-4400-aa84-46a524395b79 */
   private int insuranceId;
   
   /** @pdRoleInfo migr=no name=MedicalRecord assc=association23 mult=1..1 */
   public MedicalRecord medicalRecord;
   /** @pdRoleInfo migr=no name=Appointment assc=association24 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Appointment> appointment;
   /** @pdRoleInfo migr=no name=Clinic assc=association28 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Clinic> clinic;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<Appointment> getAppointment() {
      if (appointment == null)
         appointment = new java.util.HashSet<Appointment>();
      return appointment;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorAppointment() {
      if (appointment == null)
         appointment = new java.util.HashSet<Appointment>();
      return appointment.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newAppointment */
   public void setAppointment(java.util.Collection<Appointment> newAppointment) {
      removeAllAppointment();
      for (java.util.Iterator iter = newAppointment.iterator(); iter.hasNext();)
         addAppointment((Appointment)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newAppointment */
   public void addAppointment(Appointment newAppointment) {
      if (newAppointment == null)
         return;
      if (this.appointment == null)
         this.appointment = new java.util.HashSet<Appointment>();
      if (!this.appointment.contains(newAppointment))
         this.appointment.add(newAppointment);
   }
   
   /** @pdGenerated default remove
     * @param oldAppointment */
   public void removeAppointment(Appointment oldAppointment) {
      if (oldAppointment == null)
         return;
      if (this.appointment != null)
         if (this.appointment.contains(oldAppointment))
            this.appointment.remove(oldAppointment);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllAppointment() {
      if (appointment != null)
         appointment.clear();
   }
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

}