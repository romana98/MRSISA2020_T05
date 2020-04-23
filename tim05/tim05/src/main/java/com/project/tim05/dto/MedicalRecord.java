package com.project.tim05.dto;
/***********************************************************************
 * Module:  MedicalRecord.java
 * Author:  Vukasin
 * Purpose: Defines the Class MedicalRecord
 ***********************************************************************/

import java.util.*;

/** @pdOid 7e72db4e-fd38-45b2-8d26-e81182188ab9 */
public class MedicalRecord {
   /** @pdRoleInfo migr=no name=Disease assc=association20 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Disease> disease;
   /** @pdRoleInfo migr=no name=Appointment assc=association21 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Appointment> appointment;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<Disease> getDisease() {
      if (disease == null)
         disease = new java.util.HashSet<Disease>();
      return disease;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorDisease() {
      if (disease == null)
         disease = new java.util.HashSet<Disease>();
      return disease.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newDisease */
   public void setDisease(java.util.Collection<Disease> newDisease) {
      removeAllDisease();
      for (java.util.Iterator iter = newDisease.iterator(); iter.hasNext();)
         addDisease((Disease)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newDisease */
   public void addDisease(Disease newDisease) {
      if (newDisease == null)
         return;
      if (this.disease == null)
         this.disease = new java.util.HashSet<Disease>();
      if (!this.disease.contains(newDisease))
         this.disease.add(newDisease);
   }
   
   /** @pdGenerated default remove
     * @param oldDisease */
   public void removeDisease(Disease oldDisease) {
      if (oldDisease == null)
         return;
      if (this.disease != null)
         if (this.disease.contains(oldDisease))
            this.disease.remove(oldDisease);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllDisease() {
      if (disease != null)
         disease.clear();
   }
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

}