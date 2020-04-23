package com.project.tim05.model;
/***********************************************************************
 * Module:  Clinic.java
 * Author:  Vukasin
 * Purpose: Defines the Class Clinic
 ***********************************************************************/

import java.util.*;

/** @pdOid 3b2b2e4d-8aef-41c3-aa7d-aeca40bd2f6d */
public class Clinic {
   /** @pdOid 4e64e41d-bfa5-41f7-8a14-c1c701c33f47 */
   private String name;
   /** @pdOid 799e077b-75f4-4e13-ae6f-3f1f3cedde4b */
   private Collections ratings;
   /** @pdOid e1ac4617-57f8-4e69-b862-d9fe71740da2 */
   private String address;
   /** @pdOid 3f886d85-b995-496e-8d70-e98f84ed7783 */
   private String description;
   
   /** @pdRoleInfo migr=no name=Doctor assc=association3 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Doctor> doctor;
   /** @pdRoleInfo migr=no name=Hall assc=association4 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Hall> hall;
   public java.util.Collection association5;
   /** @pdRoleInfo migr=no name=Appointment assc=association27 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Appointment> appointment;
   
   
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