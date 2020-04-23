package com.project.tim05.dto;

import java.util.*;

public class MedicalRecordDTO {
	
   public java.util.Collection<DiseaseDTO> disease;
   public java.util.Collection<AppointmentDTO> appointment;
   
   
   public java.util.Collection<DiseaseDTO> getDisease() {
      if (disease == null)
         disease = new ArrayList<DiseaseDTO>();
      return disease;
   }
   
   public MedicalRecordDTO(Collection<DiseaseDTO> disease, Collection<AppointmentDTO> appointment) {
	super();
	this.disease = disease;
	this.appointment = appointment;
   }
   
   public MedicalRecordDTO() {
	super();
   }

public void setDisease(java.util.Collection<DiseaseDTO> disease) {
	   this.disease = disease;
   }

   public void setAppointment(java.util.Collection<AppointmentDTO> appointment) {
	   this.appointment = appointment;
   }

   public Iterator<DiseaseDTO> getIteratorDisease() {
         if (disease == null)
            disease = new ArrayList<DiseaseDTO>();
         return disease.iterator();
   }
   
   
   public void setDisease(ArrayList<DiseaseDTO> newDisease) {
      removeAllDisease();
      for (Iterator<DiseaseDTO> iter = newDisease.iterator(); iter.hasNext();)
         addDisease((DiseaseDTO)iter.next());
   }
   
   
   public void addDisease(DiseaseDTO newDisease) {
      if (newDisease == null)
         return;
      if (this.disease == null)
         this.disease = new ArrayList<DiseaseDTO>();
      if (!this.disease.contains(newDisease))
         this.disease.add(newDisease);
   }
   
  
   public void removeDisease(DiseaseDTO oldDisease) {
      if (oldDisease == null)
         return;
      if (this.disease != null)
         if (this.disease.contains(oldDisease))
            this.disease.remove(oldDisease);
   }
   
   public void removeAllDisease() {
      if (disease != null)
         disease.clear();
   }
   public java.util.Collection<AppointmentDTO> getAppointment() {
      if (appointment == null)
         appointment = new ArrayList<AppointmentDTO>();
      return appointment;
   }
   
   public Iterator<AppointmentDTO> getIteratorAppointment() {
      if (appointment == null)
         appointment = new ArrayList<AppointmentDTO>();
      return appointment.iterator();
   }
   
   
   public void setAppointment(ArrayList<AppointmentDTO> newAppointment) {
      removeAllAppointment();
      for (Iterator<AppointmentDTO> iter = newAppointment.iterator(); iter.hasNext();)
         addAppointment((AppointmentDTO)iter.next());
   }
   
   
   public void addAppointment(AppointmentDTO newAppointment) {
      if (newAppointment == null)
         return;
      if (this.appointment == null)
         this.appointment = new ArrayList<AppointmentDTO>();
      if (!this.appointment.contains(newAppointment))
         this.appointment.add(newAppointment);
   }
   
   
   public void removeAppointment(AppointmentDTO oldAppointment) {
      if (oldAppointment == null)
         return;
      if (this.appointment != null)
         if (this.appointment.contains(oldAppointment))
            this.appointment.remove(oldAppointment);
   }
   
   public void removeAllAppointment() {
      if (appointment != null)
         appointment.clear();
   }

}