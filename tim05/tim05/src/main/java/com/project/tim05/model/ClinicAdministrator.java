package com.project.tim05.model;
/***********************************************************************
 * Module:  ClinicAdministrator.java
 * Author:  Vukasin
 * Purpose: Defines the Class ClinicAdministrator
 ***********************************************************************/

import java.util.*;

/** @pdOid 2ca83f9f-d7ef-458f-b685-27bb35585358 */
public class ClinicAdministrator {
   /** @pdOid 9f6f5037-9ef9-485d-bcd1-24a07856f042 */
   private String name;
   /** @pdOid 1659d7fe-3858-4ab3-b513-69af442f2280 */
   private String surname;
   /** @pdOid 02a534dd-9aee-4fd4-a1e4-66c4568a79c4 */
   private String password;
   /** @pdOid ba0dce0d-f8ce-4f0a-bbfb-b6f44c3753ec */
   private String email;
   
   /** @pdRoleInfo migr=no name=Appointment assc=association9 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Appointment> appointment;
   /** @pdRoleInfo migr=no name=Clinic assc=association10 mult=1..1 */
   public Clinic clinic;
   /** @pdRoleInfo migr=no name=Hall assc=association11 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Hall> hall;
   /** @pdRoleInfo migr=no name=Doctor assc=association12 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Doctor> doctor;
   /** @pdRoleInfo migr=no name=AppointmentType assc=association13 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<AppointmentType> appointmentType;
   
   
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
   public java.util.Collection<Hall> getHall() {
      if (hall == null)
         hall = new java.util.HashSet<Hall>();
      return hall;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorHall() {
      if (hall == null)
         hall = new java.util.HashSet<Hall>();
      return hall.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newHall */
   public void setHall(java.util.Collection<Hall> newHall) {
      removeAllHall();
      for (java.util.Iterator iter = newHall.iterator(); iter.hasNext();)
         addHall((Hall)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newHall */
   public void addHall(Hall newHall) {
      if (newHall == null)
         return;
      if (this.hall == null)
         this.hall = new java.util.HashSet<Hall>();
      if (!this.hall.contains(newHall))
         this.hall.add(newHall);
   }
   
   /** @pdGenerated default remove
     * @param oldHall */
   public void removeHall(Hall oldHall) {
      if (oldHall == null)
         return;
      if (this.hall != null)
         if (this.hall.contains(oldHall))
            this.hall.remove(oldHall);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllHall() {
      if (hall != null)
         hall.clear();
   }
   /** @pdGenerated default getter */
   public java.util.Collection<Doctor> getDoctor() {
      if (doctor == null)
         doctor = new java.util.HashSet<Doctor>();
      return doctor;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorDoctor() {
      if (doctor == null)
         doctor = new java.util.HashSet<Doctor>();
      return doctor.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newDoctor */
   public void setDoctor(java.util.Collection<Doctor> newDoctor) {
      removeAllDoctor();
      for (java.util.Iterator iter = newDoctor.iterator(); iter.hasNext();)
         addDoctor((Doctor)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newDoctor */
   public void addDoctor(Doctor newDoctor) {
      if (newDoctor == null)
         return;
      if (this.doctor == null)
         this.doctor = new java.util.HashSet<Doctor>();
      if (!this.doctor.contains(newDoctor))
         this.doctor.add(newDoctor);
   }
   
   /** @pdGenerated default remove
     * @param oldDoctor */
   public void removeDoctor(Doctor oldDoctor) {
      if (oldDoctor == null)
         return;
      if (this.doctor != null)
         if (this.doctor.contains(oldDoctor))
            this.doctor.remove(oldDoctor);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllDoctor() {
      if (doctor != null)
         doctor.clear();
   }
   /** @pdGenerated default getter */
   public java.util.Collection<AppointmentType> getAppointmentType() {
      if (appointmentType == null)
         appointmentType = new java.util.HashSet<AppointmentType>();
      return appointmentType;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorAppointmentType() {
      if (appointmentType == null)
         appointmentType = new java.util.HashSet<AppointmentType>();
      return appointmentType.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newAppointmentType */
   public void setAppointmentType(java.util.Collection<AppointmentType> newAppointmentType) {
      removeAllAppointmentType();
      for (java.util.Iterator iter = newAppointmentType.iterator(); iter.hasNext();)
         addAppointmentType((AppointmentType)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newAppointmentType */
   public void addAppointmentType(AppointmentType newAppointmentType) {
      if (newAppointmentType == null)
         return;
      if (this.appointmentType == null)
         this.appointmentType = new java.util.HashSet<AppointmentType>();
      if (!this.appointmentType.contains(newAppointmentType))
         this.appointmentType.add(newAppointmentType);
   }
   
   /** @pdGenerated default remove
     * @param oldAppointmentType */
   public void removeAppointmentType(AppointmentType oldAppointmentType) {
      if (oldAppointmentType == null)
         return;
      if (this.appointmentType != null)
         if (this.appointmentType.contains(oldAppointmentType))
            this.appointmentType.remove(oldAppointmentType);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllAppointmentType() {
      if (appointmentType != null)
         appointmentType.clear();
   }

}