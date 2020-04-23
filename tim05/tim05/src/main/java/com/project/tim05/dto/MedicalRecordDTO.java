package com.project.tim05.dto;

import java.util.*;

public class MedicalRecordDTO {
	
   private ArrayList<DiseaseDTO> diseases;
   private ArrayList<AppointmentDTO> appointments;
   
   
   public ArrayList<DiseaseDTO> getDisease() {
      if (diseases == null)
         diseases = new ArrayList<DiseaseDTO>();
      return diseases;
   }
   
   public MedicalRecordDTO(ArrayList<DiseaseDTO> diseases, ArrayList<AppointmentDTO> appointments) {
	super();
	this.diseases = diseases;
	this.appointments = appointments;
   }
   
   public MedicalRecordDTO() {
	super();
	this.diseases = new ArrayList<>();
	this.appointments = new ArrayList<>();
   }

   public Iterator<DiseaseDTO> getIteratorDisease() {
         if (diseases == null)
            diseases = new ArrayList<DiseaseDTO>();
         return diseases.iterator();
   }
   
   
   public void setDisease(ArrayList<DiseaseDTO> newDisease) {
      removeAllDisease();
      for (Iterator<DiseaseDTO> iter = newDisease.iterator(); iter.hasNext();)
         addDisease((DiseaseDTO)iter.next());
   }
   
   
   public void addDisease(DiseaseDTO newDisease) {
      if (newDisease == null)
         return;
      if (this.diseases == null)
         this.diseases = new ArrayList<DiseaseDTO>();
      if (!this.diseases.contains(newDisease))
         this.diseases.add(newDisease);
   }
   
  
   public void removeDisease(DiseaseDTO oldDisease) {
      if (oldDisease == null)
         return;
      if (this.diseases != null)
         if (this.diseases.contains(oldDisease))
            this.diseases.remove(oldDisease);
   }
   
   public void removeAllDisease() {
      if (diseases != null)
         diseases.clear();
   }
   public java.util.Collection<AppointmentDTO> getAppointment() {
      if (appointments == null)
         appointments = new ArrayList<AppointmentDTO>();
      return appointments;
   }
   
   public Iterator<AppointmentDTO> getIteratorAppointment() {
      if (appointments == null)
         appointments = new ArrayList<AppointmentDTO>();
      return appointments.iterator();
   }
   
   
   public void setAppointment(ArrayList<AppointmentDTO> newAppointment) {
      removeAllAppointment();
      for (Iterator<AppointmentDTO> iter = newAppointment.iterator(); iter.hasNext();)
         addAppointment((AppointmentDTO)iter.next());
   }
   
   
   public void addAppointment(AppointmentDTO newAppointment) {
      if (newAppointment == null)
         return;
      if (this.appointments == null)
         this.appointments = new ArrayList<AppointmentDTO>();
      if (!this.appointments.contains(newAppointment))
         this.appointments.add(newAppointment);
   }
   
   
   public void removeAppointment(AppointmentDTO oldAppointment) {
      if (oldAppointment == null)
         return;
      if (this.appointments != null)
         if (this.appointments.contains(oldAppointment))
            this.appointments.remove(oldAppointment);
   }
   
   public void removeAllAppointment() {
      if (appointments != null)
         appointments.clear();
   }

}